package com.soethan.foodycompose.presentation.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(private val foodRepo: FoodRepo) : ViewModel() {


    private val _randomJokeState = MutableStateFlow<Resource<String>>(Resource.Idle)
    val randomJokeState: StateFlow<Resource<String>>
        get() = _randomJokeState

    init {
        loadJoke()
    }

    fun loadJoke() {
        viewModelScope.launch {
            _randomJokeState.value = Resource.Loading
            try {
                foodRepo.getRandomJoke().collectLatest {
                    _randomJokeState.value = Resource.Content(it)
                }
            } catch (e: Exception) {
                _randomJokeState.value = Resource.Error(e.message ?: "")
            }
        }

    }
}