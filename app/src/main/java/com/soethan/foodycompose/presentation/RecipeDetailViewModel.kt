package com.soethan.foodycompose.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    val foodRepo: FoodRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])


    private val _ingredients = MutableStateFlow<List<IngredientEntity>?>(null)
    val ingredientsStateFlow: StateFlow<List<IngredientEntity>?>
        get() = _ingredients

    init {
        getRecipeInformation()
    }


    fun getRecipeInformation() {
        viewModelScope.launch {
            try {
                val result = foodRepo.getRecipeInformation(recipeId)
                _ingredients.value = result.ingredients

            } catch (e: Exception) {
                Log.d("RecipeDetailViewModel", "getRecipeInformation: ${e.message} ")
            }
        }
    }

}