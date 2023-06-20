package com.dicoding.chicken.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.chicken.data.ChickenRepository
import com.dicoding.chicken.model.Chicken
import com.dicoding.chicken.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ChickenRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Chicken>>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getFavoriteChicken() = viewModelScope.launch {
        repository.getFavoriteChicken()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }


    fun updateChicken(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateChicken(id, newState)
        getFavoriteChicken()
    }
}