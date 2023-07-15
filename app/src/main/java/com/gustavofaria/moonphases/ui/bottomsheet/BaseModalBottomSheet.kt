package com.gustavofaria.moonphases.ui.bottomsheet

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseModalBottomSheet(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = modalBottomSheetState,
        containerColor = Color.DarkGray,
        dragHandle = null,
        windowInsets = WindowInsets.statusBars
    ) { content() }
}
