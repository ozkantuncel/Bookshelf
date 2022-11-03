package com.ozkan.bookshelf.ui.screens.common.dialog


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ozkan.bookshelf.R

@Composable
fun BTKDialogWithTextFiledAndButton(
    modifier: Modifier = Modifier,
    openTheDialog: MutableState<Boolean>,
    title: String? = null,
    message: String? = null,
    content: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(10.dp),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    titleTextStyle: TextStyle = TextStyle(
        fontStyle = FontStyle(R.font.metropolis_medium),
        fontSize = 25.sp
    ),
    messageTextStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.metropolis_medium)),
        color = Color.Black
    ),
    properties: DialogProperties = DialogProperties()


) {
    Dialog(
        onDismissRequest = { openTheDialog.value = !openTheDialog.value },
    ) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                openTheDialog.value = !openTheDialog.value
            },
            buttons = {
                content()
            },
            properties = properties,
            contentColor = contentColor,
            backgroundColor = backgroundColor,
            shape = shape,
            text = {
                message?.let {
                    Text(text = message, style = messageTextStyle)
                }
            },
            title = {
                title?.let {
                    Text(text = title, style = titleTextStyle)
                }
            }
        )
    }


}