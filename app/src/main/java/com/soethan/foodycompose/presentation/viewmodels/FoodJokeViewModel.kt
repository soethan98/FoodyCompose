package com.soethan.foodycompose.presentation.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.onError
import com.soethan.foodycompose.domain.onException
import com.soethan.foodycompose.domain.onSuccess
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.domain.repo.UserSettingRepo
import com.soethan.foodycompose.utils.AppTheme
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(
    private val foodRepo: FoodRepo,
) : ViewModel() {


    private val _randomJokeState = MutableStateFlow<Resource<String>>(Resource.Idle)
    val randomJokeState: StateFlow<Resource<String>>
        get() = _randomJokeState


    init {
        loadJoke()
    }

    private fun loadJoke() {
        viewModelScope.launch {
            _randomJokeState.value = Resource.Loading
            foodRepo.getRandomJoke().collectLatest { dataState: DataState<String> ->
                dataState.onSuccess {
                    _randomJokeState.value = Resource.Content(it)
                }
                    .onError { code, message ->
                        _randomJokeState.value =
                            Resource.Error(message = message ?: "Error Happened")

                    }.onException { e ->
                        _randomJokeState.value =
                            Resource.Error(message = e.message ?: "Error Happened")

                    }
            }

        }
    }


}