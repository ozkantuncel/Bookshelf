package com.ozkan.bookshelf.firebase.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.ozkan.bookshelf.firebase.dto.User
import com.ozkan.bookshelf.firebase.remote.AuthRepository
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.util.FirebaseFireStoreConstants
import com.ozkan.bookshelf.util.pref.Prefs

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore,
    private val gson: Gson
) : AuthRepository {

    override fun registerUser(
        email: String,
        password: String,
        user: User,
        result: (UiState<String>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    user.id = it.result.user?.uid ?: ""
                    updateUserInfo(user) { state ->
                        when (state) {
                            is UiState.Success -> {
                                storeSession(id = it.result.user?.uid ?: "", isLogin = false) {
                                    if (it == null) {
                                        result.invoke(UiState.Failure("User register successfully but session failed to store"))
                                    } else {
                                        result.invoke(UiState.Success("User register successfully!"))
                                    }
                                }
                            }
                            is UiState.Failure -> {
                                result.invoke(UiState.Failure(state.error))
                            }
                            else -> {

                            }
                        }

                    }
                } else {
                    try {
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        result.invoke(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        result.invoke(UiState.Failure("Authentication failed, Invalid email entered"))
                    } catch (e: FirebaseAuthUserCollisionException) {
                        result.invoke(UiState.Failure("Authentication failed, Email already registered."))
                    } catch (e: Exception) {
                        result.invoke(UiState.Failure(e.message))
                    }
                }
            }.addOnFailureListener {
                result.invoke(
                    UiState.Failure(it.localizedMessage)
                )
            }
    }

    override fun loginUser(
        email: String,
        password: String,
        rememberMeState: Boolean,
        result: (UiState<String>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    storeSession(
                        id = task.result.user?.uid?:"",
                        rememberMeState = rememberMeState,
                        isLogin = true
                    ){
                        if (it == null) {
                            result.invoke(UiState.Failure("Failed to store local session"))
                        } else {
                            result.invoke(UiState.Success("Login successfully!"))
                        }
                    }
                }
            }.addOnCompleteListener {
                result.invoke(UiState.Failure("Authentication failed, Check email and password"))
            }
    }

    override fun storeSession(id: String, result: (User?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun storeSession(
        isLogin: Boolean,
        id: String,
        rememberMeState: Boolean,
        result: (User?) -> Unit
    ) {
        database.collection(FirebaseFireStoreConstants.USERS).document(id)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = it.result.toObject(User::class.java)
                    if (isLogin) {
                        if (rememberMeState) {
                            Prefs.setRememberMeState(true)
                        } else {
                            Prefs.setRememberMeState(false)
                        }
                    } else {
                        Prefs.setRememberMeState(true)
                    }
                    Prefs.setUserSession(gson.toJson(user))
                    result.invoke(user)
                } else {
                    result.invoke(null)
                }
            }
            .addOnFailureListener {
                result.invoke(null)
            }
    }

    override fun updateUserInfo(
        user: User,
        result: (UiState<String>) -> Unit)
    {
        val document = database.collection(FirebaseFireStoreConstants.USERS).document(user.id)
        document.set(user)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("User has been update successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun forgotPassword(email: String, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun logout(result: () -> Unit) {
        auth.signOut()
        Prefs.setUserSession(null)
        Prefs.setRememberMeState(false)
        result.invoke()
    }

    override fun getSession(result: (User?) -> Unit) {
        TODO("Not yet implemented")
    }

}