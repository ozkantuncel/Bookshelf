package com.ozkan.bookshelf.ui.screens.common.button

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.ui.theme.Navyblue
import com.ozkan.bookshelf.util.extension.toast

@Composable
fun BKAIconButton(
    modifier: Modifier,
    @DrawableRes icon: Int? = null,
    enabled: Boolean = true,
    iconContentDescription: String? = null,
    iconModifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified,
    onButtonClick: () -> Unit
) {
    IconButton(
        onClick = { onButtonClick() },
        modifier = modifier,
        enabled = enabled,
    ) {
        icon?.let {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = iconContentDescription,
                modifier = iconModifier,
                tint = iconTint
            )
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 55.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Navyblue
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Geç")
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun BTKFinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 55.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Navyblue
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Geç")
            }
        }
    }
}

@Composable
fun BTKLoginButton(
    modifier: Modifier,
    modifierButton: Modifier,
    text: String,
    fontSize: TextUnit = 25.sp,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            modifier = modifierButton,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Navyblue
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = text,
                fontSize = fontSize
            )
        }
    }
}

@Composable
fun BTKAddCartButton(
    modifier: Modifier=Modifier,
    modifierButton: Modifier=Modifier,
    text: String = "Ekle",
    fontSize: TextUnit = 18.sp,
    onClick: () -> Unit
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            modifier = modifierButton,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Navyblue
            ),
            shape = RoundedCornerShape(50)
        ) {
            Icon(painter = painterResource(id = R.drawable.basket_ico) ,
                contentDescription =null 
            )
            Text(
                text = text,
                fontSize = fontSize
            )
        }
    }
}

@Composable
fun BTKButtonItem(
    item:MutableState<Int>,
    activity:Activity,
    onChange:(items:Int) ->Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BKAIconButton(
            modifier = Modifier,
            iconModifier = Modifier.size(35.dp),
            icon = R.drawable.min_ico,
            iconTint = Color.Black,
            iconContentDescription = null
        ) {
            if (item.value > 1) {
                item.value -= 1
                onChange.invoke(item.value)
            }
        }
        Text(
            text = "${item.value}",
            color = Navyblue,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
        BKAIconButton(
            modifier = Modifier,
            iconModifier = Modifier.size(35.dp),
            icon = R.drawable.pls_ico,
            iconTint = Color.Black,
            iconContentDescription = null
        ) {
            if (item.value < 5) {
                item.value += 1
                onChange.invoke(item.value)
            } else {
                activity.toast("5'den fazla sipariş verilemez")
            }
        }
    }
}