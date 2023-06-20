package com.dicoding.chicken.ui.screen.favorite

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dicoding.chicken.R
import com.dicoding.chicken.model.Chicken
import com.dicoding.chicken.ui.common.UiState
import com.dicoding.chicken.ui.component.EmptyContent
import com.dicoding.chicken.ui.screen.home.ListChicken
import com.dicoding.chicken.ui.theme.ChickenTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value

    when (uiState) {
        is UiState.Error -> {
            // Handle error state
        }
        is UiState.Loading -> {
            viewModel.getFavoriteChicken()
        }
        is UiState.Success -> {
            FavoriteContent(
                chickenItem = uiState.data,
                navigateToDetail = navigateToDetail,
                onFavClick = { id, newState ->
                    viewModel.updateChicken(id, newState)
                },
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    chickenItem: List<Chicken>,
    onFavClick: (id: Int, newState: Boolean) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.favorite_page))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        modifier = modifier
    ) {
        if (chickenItem.isNotEmpty()) {
            ListChicken(
                chickenItem = chickenItem,
                onFavClick = onFavClick,
                contentPadding = it,
                navigateToDetail = navigateToDetail,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            EmptyContent(
                image = R.drawable.emptydata,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    ChickenTheme {
        FavoriteScreen(navigateToDetail = {})
    }
}
