package com.dicoding.chicken.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.chicken.R
import com.dicoding.chicken.ui.theme.ChickenTheme

@Composable
fun EmptyContent(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .testTag("empty_content")
    ) {
        ImageWithContentScale(image = image)
    }
}

@Composable
fun ImageWithContentScale(@DrawableRes image: Int) {
    AsyncImage(
        model = image,
        contentDescription = stringResource(R.string.empty_image),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun EmptyContentBoxPreview() {
    ChickenTheme {
        EmptyContent(image = R.drawable.emptydata)
    }
}