package com.gustavofaria.moonphases.ui.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gustavofaria.moonphases.theme.MoonPhasesTheme
import com.gustavofaria.moonphases.utils.ShimmerBrush

@Composable
fun HomeShimmer(isLoading: Boolean) {
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Column {
                Text(
                    modifier = Modifier
                        .background(
                            brush = ShimmerBrush(showShimmer = isLoading),
                            shape = RoundedCornerShape(size = 8.dp),
                        ),
                    text = "Lua Minguante",
                    style = MaterialTheme.typography.displayLarge
                        .copy(color = Color.Transparent)
                )
                Spacer(modifier = Modifier.size(size = 4.dp))
                Text(
                    modifier = Modifier
                        .background(
                            brush = ShimmerBrush(showShimmer = isLoading),
                            shape = RoundedCornerShape(size = 8.dp),
                        ),
                    text = "13 de julho, 2023",
                    style = MaterialTheme.typography.displaySmall
                        .copy(color = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
        }
        Spacer(
            modifier = Modifier
                .defaultMinSize(minHeight = 16.dp)
                .weight(weight = 1f)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = ShimmerBrush(showShimmer = isLoading),
                    shape = RoundedCornerShape(size = 8.dp),
                ),
            text = "A lua de hoje é Lua Minguante\ne está 20% visível",
            style = MaterialTheme.typography.displayMedium
                .copy(color = Color.Transparent),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .size(size = 224.dp)
                .padding(all = 24.dp)
                .background(
                brush = ShimmerBrush(showShimmer = isLoading),
                shape = CircleShape,
            ),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = ShimmerBrush(showShimmer = isLoading),
                    shape = RoundedCornerShape(size = 8.dp),
                ),
            text = "Faltam 4 dias para\nLua Nova",
            style = MaterialTheme.typography.displayMedium
                .copy(color = Color.Transparent),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier
                .defaultMinSize(minHeight = 16.dp)
                .weight(weight = 1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    MoonPhasesTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeShimmer(isLoading = true)
        }
    }
}
