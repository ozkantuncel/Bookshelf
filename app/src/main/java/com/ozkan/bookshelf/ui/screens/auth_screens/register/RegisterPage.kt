package com.ozkan.bookshelf.ui.screens.auth_screens.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.navigation.Screen
import com.ozkan.bookshelf.ui.screens.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.screens.common.button.BKAIconButton
import com.ozkan.bookshelf.ui.screens.common.button.FinishButton
import com.ozkan.bookshelf.ui.screens.common.button.BTKFinishButton
import com.ozkan.bookshelf.ui.screens.common.button.BTKLoginButton
import com.ozkan.bookshelf.ui.theme.AppBlue


@Composable
fun RegisterPage(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerState = viewModel.registerState.value
    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }

    when (registerState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }
        is UiState.Failure -> {
            registerState.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }
        is UiState.Success -> {

            LaunchedEffect(true) {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }
        is UiState.Empty -> {}
    }

    RegisterPage(
        viewModel = viewModel,
        errorDialogState = errorDialogState,
        errorTitle = errorTitle.value
    )

}

@Composable
fun RegisterPage(
    viewModel: RegisterViewModel,
    errorDialogState: MutableState<Boolean>,
    errorTitle: String
) {
    val userEmail = remember { mutableStateOf("") }
    val userNameSurname = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    BKAIconButton(
                        modifier = Modifier,
                        iconModifier = Modifier.size(35.dp),
                        icon = R.drawable.left_ico,
                        iconTint = Color.Gray
                    ) {
                        //Eklenecek
                    }
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 35.dp, vertical = 10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(0.35f),
                    painter = painterResource(id = R.drawable.signup),
                    contentDescription = null,
                    )

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Kayıt Ol",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier.fillMaxWidth()) {

                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.pers_ico),
                        contentDescription = null
                    )
                    OutlinedTextField(
                        textStyle = TextStyle(textAlign = TextAlign.Start),
                        value = userNameSurname.value,
                        onValueChange = {
                            userNameSurname.value = it
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
                                text = "Kulanıcı İsmi",
                                fontSize = 15.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
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

                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
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
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                BTKLoginButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Kaydol",
                    modifierButton = Modifier
                        .height(50.dp)
                        .width(300.dp)
                , fontSize = 20.sp
                ) {
                    if (viewModel.checkEmailPasswordNameState(
                            email = userEmail.value,
                            password = password.value,
                            nameSurname = userNameSurname.value
                        )
                    ) {
                        viewModel.register(
                            email = userEmail.value,
                            password = password.value,
                            nameSurname = userNameSurname.value
                        )
                    } else {
                        //no op
                    }
                }

            }
        }
    )

}


