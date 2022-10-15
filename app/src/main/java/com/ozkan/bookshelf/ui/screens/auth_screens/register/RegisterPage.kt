package com.ozkan.bookshelf.ui.screens.auth_screens.register


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.navigation.Screen
import com.ozkan.bookshelf.ui.screens.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.theme.AppBlue
import com.ozkan.bookshelf.ui.theme.Navyblue


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


            LaunchedEffect(true){
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

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp, vertical = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kayıt Ol",
            fontSize = 35.sp,
            textAlign = TextAlign.Center
        )
        print("print ic")
        Spacer(modifier = Modifier.height(42.dp))

        OutlinedTextField(
            textStyle = TextStyle(textAlign = TextAlign.Start),
            value = userNameSurname.value,
            onValueChange = {
                userNameSurname.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppBlue,
                cursorColor = AppBlue
            ),
            placeholder = {
                Text(
                    text = "İsim ve Soyisim",
                    fontSize = 10.sp
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(42.dp))

        OutlinedTextField(
            textStyle = TextStyle(textAlign = TextAlign.Start),
            value = userEmail.value,
            onValueChange = {
                userEmail.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppBlue,
                cursorColor = AppBlue
            ),
            placeholder = {
                Text(
                    text = "Email",
                    fontSize = 10.sp
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(42.dp))

        OutlinedTextField(
            textStyle = TextStyle(textAlign = TextAlign.Start),
            value = password.value,
            onValueChange = {
                password.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppBlue,
                cursorColor = AppBlue
            ),
            placeholder = {
                Text(
                    text = "Password",
                    fontSize = 10.sp
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        FinishButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "Kaydol"
        ) {
            if(viewModel.checkEmailPasswordNameState(
                    email = userEmail.value,
                    password = password.value,
                    nameSurname = userNameSurname.value
            )){
                viewModel.register(
                    email = userEmail.value,
                    password = password.value,
                    nameSurname = userNameSurname.value
                )
            }else {
                //no op
            }
        }

    }
}


@Composable
fun FinishButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 55.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Navyblue
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = text)
        }

    }
}