package com.ozkan.bookshelf.ui.screens.common.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ozkan.bookshelf.ui.theme.Navyblue

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
}@ExperimentalAnimationApi
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