package com.example.news_app.presentation.newsui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(all = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(count = 5) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Transparent)
            .padding(all = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxHeight()
                .weight(1f)
                .background(Color(0xFFE3E3E3))
        )
        Spacer(modifier = androidx.compose.ui.Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Cyan),
                color = Color(0xFFE3E3E3)
            ) {}
            Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
            repeat(3) {
                Box(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth()
                        .height(18.dp)
                        .background(Color(0xFFE3E3E3))
                )
                Spacer(modifier = androidx.compose.ui.Modifier.height(6.dp))
            }
        }
    }
}

@Composable
@Preview
fun ShimmerItemPreview() {
    AnimatedShimmerItem()
}

@Composable
fun SliderShimmerItem(
    alpha: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 5f)
            .height(180.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .padding(top = 4.dp, bottom = 4.dp, end = 8.dp, start = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth()
                    .height(25.dp)
                    .background(Color(0xFFE3E3E3)),
            ) {}

            Box(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFE3E3E3))
            ) {}
            Box(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .width(150.dp)
                    .height(25.dp)
                    .background(Color(0xFFE3E3E3))
            ) {}
        }

        Box(
            modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxHeight()
                .weight(2f)
                .padding(top = 4.dp, bottom = 4.dp, end = 8.dp)
                .background(Color(0xFFE3E3E3)),
        ) {}
    }
}

@Composable
fun AnimatedSliderShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    SliderShimmerItem(alpha = alphaAnim)
}

@Preview
@Composable
fun SliderShimmerPreview() {
    AnimatedSliderShimmerItem()
}
