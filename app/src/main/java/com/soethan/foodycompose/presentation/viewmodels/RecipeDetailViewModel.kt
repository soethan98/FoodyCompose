package com.soethan.foodycompose.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.onError
import com.soethan.foodycompose.domain.onException
import com.soethan.foodycompose.domain.onSuccess
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import com.soethan.foodycompose.utils.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _recipeDetailState = MutableStateFlow<Resource<RecipeEntity>>(Resource.Idle)
    val recipeDetailState: StateFlow<Resource<RecipeEntity>>
        get() = _recipeDetailState


    init {
        getRecipeInformation()
    }


    private fun getRecipeInformation() {
        viewModelScope.launch {

            _recipeDetailState.value = Resource.Loading
            val result = foodRepo.getRecipeInformation(recipeId)
            result.collectLatest { dataState ->
                dataState.onSuccess {
                    _recipeDetailState.value = Resource.Content(it)
                }.onError { code, message ->
                    _recipeDetailState.value =
                        Resource.Error(message = message ?: "Error Happened")

                }.onException { exception ->
                    _recipeDetailState.value =
                        Resource.Error(message = exception.message ?: "Error Happened")
                }
            }

        }
    }


    fun toggleFavorite(){

    }


    fun addToFavorite() {
        viewModelScope.launch {
            val currentRecipe = recipeDetailState.value.data()
            currentRecipe?.let {
                foodRepo.addToFav(it)
            }
        }
    }


    fun isInFavorite(){

    }

    fun removeFromFavorite(){
        viewModelScope.launch {
            foodRepo.removeFromFav(recipeId.toInt())
        }
    }


}


data class RecipeType(
    val title: String,
    val isMet: Boolean
)