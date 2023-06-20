package com.dicoding.chicken.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.dicoding.chicken.R
import com.dicoding.chicken.ui.common.UiState
import com.dicoding.chicken.ui.theme.ChickenTheme

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value

    when (uiState) {
        is UiState.Error -> {
            // Handle error state
        }
        is UiState.Loading -> {
            viewModel.getChickenById(id)
        }
        is UiState.Success -> {
            val data = uiState.data
            DetailContent(
                urlImage = data.gambar,
                title = data.judul,
                description = data.deskripsi,
                navigateBack = navigateBack
            )
        }
    }
}

@Composable
fun DetailContent(
    urlImage: String,
    title: String,
    description: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            NetworkImage(
                url = urlImage,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(264.dp)
            )
            BackButton(
                onClick = navigateBack,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        content = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back_button)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    ChickenTheme {
        DetailContent(
            urlImage = "https://c4.wallpaperflare.com/wallpaper/849/490/357/fantasy-art-artwork-magic-the-gathering-prognostic-sphinx-wallpaper-preview.jpg",
            title = "Hydra",
            description = "Lorem Ipsum Kodomo Momodo Syana",
            navigateBack = {}
        )
    }
}
