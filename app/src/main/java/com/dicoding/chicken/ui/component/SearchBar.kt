package com.dicoding.chicken.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dicoding.chicken.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String, Boolean) -> Unit,
    active: Boolean,
    history: List<String>,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes searchPlaceholder: Int = R.string.search,
    @StringRes searchIconContentDescription: Int = R.string.search_icon,
    @StringRes closeButtonContentDescription: Int = R.string.close_button,
    @StringRes historyIconContentDescription: Int = R.string.history_icon,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = {
                onActiveChange(false)
                onSearch(it, true)
            },
            active = active,
            onActiveChange = onActiveChange,
            placeholder = {
                Text(stringResource(searchPlaceholder))
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onActiveChange(false)
                        onSearch(query, true)
                    },
                    modifier = Modifier.testTag("search_icon")
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(searchIconContentDescription),
                    )
                }
            },
            trailingIcon = {
                if (active) {
                    IconButton(onClick = {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                        } else {
                            onActiveChange(false)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(closeButtonContentDescription),
                        )
                    }
                }
            }
        ) {
            history.forEach {
                HistoryRow(
                    text = it,
                    onActiveChange = onActiveChange,
                    onSearch = onSearch,
                    historyIconContentDescription = historyIconContentDescription
                )
            }
        }
    }
}

@Composable
private fun HistoryRow(
    text: String,
    onActiveChange: (Boolean) -> Unit,
    onSearch: (String, Boolean) -> Unit,
    @StringRes historyIconContentDescription: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onActiveChange(false)
                onSearch(text, false)
            }
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Default.History,
            contentDescription = stringResource(historyIconContentDescription)
        )
        Text(text = text, modifier = Modifier.padding(vertical = 16.dp))
    }
}