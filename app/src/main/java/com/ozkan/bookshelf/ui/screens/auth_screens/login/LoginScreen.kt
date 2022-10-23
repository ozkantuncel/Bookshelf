package com.ozkan.bookshelf.ui.screens.auth_screens.login

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.navigation.Screen
import com.ozkan.bookshelf.ui.screens.common.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.screens.common.button.BTKLoginButton
import com.ozkan.bookshelf.ui.screens.common.dialog.BTKDialogWithTextFiledAndButton
import com.ozkan.bookshelf.ui.theme.AppBac
import com.ozkan.bookshelf.ui.theme.AppBlue
import com.ozkan.bookshelf.ui.theme.Navyblue
import com.ozkan.bookshelf.util.extension.hideKeyboard
import com.ozkan.bookshelf.util.extension.toast


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.value
    val forgotPasswordState = viewModel.forgotPassword.value
    val forgotPasswordDialogState = remember { mutableStateOf(false) }
    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }
    val activity = LocalContext.current as Activity

    when (loginState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }

        is UiState.Success -> {
            activity.toast(loginState.data)
            LaunchedEffect(true) {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }

        is UiState.Failure -> {
            loginState.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }

        is UiState.Empty -> {}
    }

    when (forgotPasswordState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }

        is UiState.Failure -> {
            forgotPasswordState.error?.let {
                activity.toast(it)
            }
        }

        is UiState.Success -> {
            activity.toast(forgotPasswordState.data)
        }

        is UiState.Empty -> {}
    }

    LoginScreenPage(
        viewModel = viewModel,
        navController = navController,
        forgotPasswordDialogState = forgotPasswordDialogState,
        activity = activity,
        errorDialogState = errorDialogState,
        errorTitle = errorTitle.value,

        )

}

@Composable
fun LoginScreenPage(
    viewModel: LoginViewModel,
    navController: NavController,
    forgotPasswordDialogState: MutableState<Boolean>,
    activity: Activity,
    errorDialogState: MutableState<Boolean>,
    errorTitle: String,
) {
    val userEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val forgotUserEmail = remember { mutableStateOf("") }
    val rememberMeState = remember { mutableStateOf(false) }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(AppBac)
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.5f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Hoşgeldiniz",
                color = Navyblue,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pharetra eu vel sagittis placerat duis cras. Facilisis ac neque.",
                textAlign = TextAlign.Justify,
                maxLines = 3,
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.at_ico),
                contentDescription = null
            )

            OutlinedTextField(
                textStyle = TextStyle(textAlign = TextAlign.Start),
                value = userEmail.value,
                onValueChange = {
                    userEmail.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppBlue,
                    cursorColor = AppBlue
                ),
                placeholder = {
                    Text(
                        text = "Mail Adresi",
                        fontSize = 15.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.pass_ico),
                contentDescription = null
            )

            OutlinedTextField(
                textStyle = TextStyle(textAlign = TextAlign.Start),
                value = password.value,//add one that
                onValueChange = {
                    password.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppBlue,
                    cursorColor = AppBlue
                ),
                placeholder = {
                    Text(
                        text = "Password",
                        fontSize = 15.sp
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        painter = if (passwordVisible.value) painterResource(id = R.drawable.opened_eye_ico) else painterResource(
                            id = R.drawable.closed_eye_ico
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                passwordVisible.value = !passwordVisible.value
                            }
                    )
                },
                keyboardActions = KeyboardActions(onDone = { activity.hideKeyboard() })
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = rememberMeState.value,
                    onClick = {
                        rememberMeState.value = !rememberMeState.value
                    },
                    colors = RadioButtonDefaults.colors(selectedColor = Navyblue)
                )

                Text(
                    text = "Beni Hatırla",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .wrapContentWidth()
                        .clickable {
                            rememberMeState.value = !rememberMeState.value
                        }
                )
            }
            Text(
                text = "Şifremi Unutum",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .wrapContentWidth()
                    .clickable {
                        forgotPasswordDialogState.value = true;
                    }
            )
        }

        Spacer(modifier = Modifier.weight(0.10f))

        BTKLoginButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Giriş Yap",
            modifierButton = Modifier
                .height(50.dp)
                .width(350.dp), fontSize = 20.sp
        ) {
            if (viewModel.checkEmailAndPasswordState(
                    email = userEmail.value,
                    password = password.value,
                )
            ) {
                viewModel.login(
                    email = userEmail.value,
                    password = password.value,
                    rememberMeState = rememberMeState.value
                )
            } else {
                activity.toast("Bilgiler uyuşmuyor")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Üyelik oluşturun",
                fontSize = 16.sp,

                )


            Text(
                modifier = Modifier
                    .padding(
                        start = 5.dp
                    )
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(Screen.Register.route)
                    },
                text = "Tıklayın",
                color = Navyblue,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }



    if (forgotPasswordDialogState.value) {
        BTKDialogWithTextFiledAndButton(
            openTheDialog = forgotPasswordDialogState,
            content = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    OutlinedTextField(
                        textStyle = TextStyle(textAlign = TextAlign.Start, color = Color.Black),
                        value = forgotUserEmail.value,
                        onValueChange = {
                            forgotUserEmail.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = AppBlue,
                            cursorColor = AppBlue
                        ),
                        placeholder = {
                            Text(
                                text = "Mail Adresi",
                                fontSize = 15.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        keyboardActions = KeyboardActions(onDone = { activity.hideKeyboard() })
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                all = 5.dp
                            ),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AppBlue),
                        onClick = {
                            forgotPasswordDialogState.value = false
                            if (forgotUserEmail.value.isNotEmpty()) {
                                viewModel.forgotPassword(email = forgotUserEmail.value)
                            } else {
                                activity.toast("Mail adresnizi giriniz!")
                            }
                        }) {
                        Text(
                            text = "Mail Gönder",
                            style = TextStyle(textAlign = TextAlign.Center)
                        )

                    }

                }
            },
            title = "Mail Adresnizi Giriniz"
        )
    }

    if (errorDialogState.value) {
        BTKDialogWithTextFiledAndButton(
            openTheDialog = errorDialogState,
            content = {},
            //title = ,
            message = errorTitle
        )
    }
}