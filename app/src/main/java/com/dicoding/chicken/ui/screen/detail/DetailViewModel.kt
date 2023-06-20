package com.dicoding.chicken.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.chicken.data.ChickenRepository
import com.dicoding.chicken.model.Chicken
import com.dicoding.chicken.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: ChickenRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Chicken>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Chicken>> get() = _uiState

    fun getChickenById(id: Int) {
        viewModelScope.launch {
            repository.getChickenById(id)
                .catch { throwable ->
                    _uiState.value = UiState.Error(throwable.message.toString())
                }
                .collect { chicken ->
                    _uiState.value = UiState.Success(chicken)
                }
        }
    }
}