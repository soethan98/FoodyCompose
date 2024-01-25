package com.soethan.foodycompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.onError
import com.soethan.foodycompose.domain.onException
import com.soethan.foodycompose.domain.onSuccess
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(private val foodRepo: FoodRepo) : ViewModel() {
    private val _favRecipeListStateFlow =
        MutableStateFlow<Resource<List<RecipeEntity>>>(Resource.Idle)
    val favRecipeListStateFlow: StateFlow<Resource<List<RecipeEntity>>>
        get() = _favRecipeListStateFlow


    init {
        getFavoriteRecipe()
    }

    private fun getFavoriteRecipe() {
        _favRecipeListStateFlow.value = Resource.Loading

        viewModelScope.launch {
            foodRepo.getAllFavRecipes().collectLatest { dataState ->
                dataState.onSuccess {
                    _favRecipeListStateFlow.value = Resource.Content(data = it)

                }.onError { code, message ->
                    _favRecipeListStateFlow.value =
                        Resource.Error(message = message ?: "Error Happened")

                }.onException {
                    _favRecipeListStateFlow.value =
                        Resource.Error(message = it.message ?: "Error Happened")

                }
            }
        }
    }
}