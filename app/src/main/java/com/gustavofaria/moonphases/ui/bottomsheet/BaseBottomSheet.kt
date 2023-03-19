package com.gustavofaria.moonphases.ui.bottomsheet

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.theme.MoonPhasesTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun showBottomSheet(
    context: Context,
    title: String,
    body: AnnotatedString,
    button: String,
    onClickButton: () -> Unit = {}
) {
    val viewGroup = (context as Activity).findViewById(android.R.id.content) as ViewGroup

    viewGroup.addView(
        ComposeView(viewGroup.context).apply {
            setContent {
                val coroutineScope = rememberCoroutineScope()
                val modalBottomSheetState =
                    rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

                var isSheetOpened by remember { mutableStateOf(value = false) }
                MoonPhasesTheme {
                    ModalBottomSheetLayout(
                        sheetBackgroundColor = Color.Transparent,
                        sheetState = modalBottomSheetState,
                        sheetContent = {
                            Column(
                                modifier = Modifier
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStart = 16.dp,
                                            topEnd = 16.dp
                                        )
                                    )
                                    .background(Color.DarkGray)
                                    .padding(all = 16.dp)
                                    .padding(bottom = 8.dp)
                                    .fillMaxWidth()
                            ) {
                                Column {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = title,
                                        style = MaterialTheme.typography.displayLarge
                                            .copy(fontSize = 24.sp)
                                    )
                                    Spacer(modifier = Modifier.size(size = 12.dp))
                                    Text(
                                        modifier = Modifier,
                                        text = body,
                                        style = MaterialTheme.typography.displayMedium
                                            .copy(fontSize = 16.sp)
                                    )
                                    Spacer(modifier = Modifier.size(size = 24.dp))

                                    Button(
                                        modifier = Modifier.height(height = 52.dp).fillMaxWidth(),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.White,
                                            contentColor = Color.Black
                                        ),
                                        elevation = null,
                                        contentPadding = PaddingValues(
                                            horizontal = 16.dp,
                                            vertical = 0.dp
                                        ),
                                        shape = RoundedCornerShape(8.dp),
                                        onClick = {
                                            coroutineScope.launch {
                                                modalBottomSheetState.hide()
                                                onClickButton()
                                            }
                                        }
                                    ) {
                                        Text(
                                            text = button,
                                            style = MaterialTheme.typography.displayLarge
                                                .copy(fontSize = 16.sp, color = Color.Black)
                                        )
                                    }
                                }
                            }
                        }
                    ) {}

                    BackHandler { coroutineScope.launch { modalBottomSheetState.hide() } }

                    LaunchedEffect(modalBottomSheetState.currentValue) {
                        when (modalBottomSheetState.currentValue) {
                            ModalBottomSheetValue.Hidden -> {
                                when {
                                    isSheetOpened -> viewGroup.removeView(this@apply)
                                    else -> {
                                        isSheetOpened = true
                                        modalBottomSheetState.show()
                                    }
                                }
                            }
                            else -> Unit
                        }
                    }
                }
            }
        }
    )
}
