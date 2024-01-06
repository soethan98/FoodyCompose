package com.soethan.foodycompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(private val foodRepo: FoodRepo) : ViewModel() {
    private val _recipeListStateFlow = MutableStateFlow<Resource<List<RecipeEntity>>>(Resource.Idle)
    val recipeListStateFlow: StateFlow<Resource<List<RecipeEntity>>>
        get() = _recipeListStateFlow

    init {
        getRandomRecipes()
    }

    fun getRandomRecipes() {


        viewModelScope.launch {
            _recipeListStateFlow.value = Resource.Loading
            foodRepo.getRandomRecipes().collectLatest { dataState ->
                if (dataState is DataState.Success) {
                    _recipeListStateFlow.value = Resource.Content(data = dataState.data)
                    return@collectLatest
                }
                if (dataState is DataState.Error) {
                    _recipeListStateFlow.value = Resource.Error(message = dataState.message ?: "Error Happened")
                    return@collectLatest
                }
                if (dataState is DataState.Exception) {
                    _recipeListStateFlow.value = Resource.Error(message = dataState.e.message ?: "Error Happened")
                    return@collectLatest
                }

            }

        }
    }

}