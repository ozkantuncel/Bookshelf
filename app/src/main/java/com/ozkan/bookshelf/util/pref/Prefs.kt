package com.ozkan.bookshelf.util.pref

object Prefs {
    private val pref: Pref = HawkImpl

    init {
        pref.init()
    }


    const val LOCAL_SHARED_PREF = "local_shared_pref"
    private const val REMEMBER_ME_STATE = "remember_me_state"
    private const val USER_SESSION = "user_session"


    fun rememberMeState(): Boolean {
        return pref.get(REMEMBER_ME_STATE, false)
    }

    fun setRememberMeState(rememberState: Boolean) {
        pref.put(REMEMBER_ME_STATE, rememberState)
    }

    fun getUserSession(): String? {
        return pref.get(USER_SESSION, null)
    }

    fun setUserSession(user: String?) {
        pref.put(USER_SESSION, user)
    }
}