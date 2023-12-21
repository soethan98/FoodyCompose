package com.soethan.foodycompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            try {
                _recipeListStateFlow.value = Resource.Loading

                val result = foodRepo.getRandomRecipes()
                _recipeListStateFlow.value = Resource.Content(data = result)
            } catch (e: Exception) {
                _recipeListStateFlow.value = Resource.Error(e.message ?: "Error happend")
            }
        }
    }

}