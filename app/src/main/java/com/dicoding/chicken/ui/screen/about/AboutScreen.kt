package com.dicoding.chicken.ui.screen.about

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.chicken.R
import com.dicoding.chicken.ui.theme.ChickenTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = modifier) {
            AsyncImage(
                model = stringResource(R.string.url_cover),
                contentDescription = stringResource(R.string.cover_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(172.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 80.dp),
            ) {
            AsyncImage(
                model = stringResource(R.string.url_profile),
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .border(2.dp, Color.White, CircleShape)
                    .clip(CircleShape)
                    .size(160.dp)
            )
            }
        }
        AboutInformation(
            name = stringResource(R.string.name),
            email = stringResource(R.string.email),
            description = stringResource(R.string.description))
    }
}

@Composable
fun AboutInformation(
    name: String,
    email: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(24.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = email,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ChickenTheme {
        AboutScreen()
    }
}