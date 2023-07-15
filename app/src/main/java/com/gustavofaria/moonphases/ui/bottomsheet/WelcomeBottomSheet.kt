package com.gustavofaria.moonphases.ui.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.extension.setBold

@Composable
fun WelcomeBottomSheet(
    onDismissRequest: () -> Unit,
    onClickButton: () -> Unit = {}
) {
    BaseModalBottomSheet(
        onDismissRequest = { onDismissRequest() }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.home_bottom_sheet_title),
                    style = MaterialTheme.typography.displayLarge
                        .copy(fontSize = 24.sp)
                )
                Spacer(modifier = Modifier.size(size = 12.dp))
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.home_bottom_sheet_body)
                        .setBold(
                            stringResource(id = R.string.home_bottom_sheet_body_bold_1),
                            stringResource(id = R.string.home_bottom_sheet_body_bold_2)
                        ),
                    style = MaterialTheme.typography.displayMedium
                        .copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.size(size = 24.dp))

                Button(
                    modifier = Modifier
                        .height(height = 52.dp)
                        .fillMaxWidth(),
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
                        onDismissRequest()
                        onClickButton()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.home_bottom_sheet_button),
                        style = MaterialTheme.typography.displayLarge
                            .copy(fontSize = 16.sp, color = Color.Black)
                    )
                }
            }
        }
    }
}
