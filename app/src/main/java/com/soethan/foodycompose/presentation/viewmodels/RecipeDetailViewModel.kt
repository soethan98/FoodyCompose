package com.soethan.foodycompose.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.onError
import com.soethan.foodycompose.domain.onException
import com.soethan.foodycompose.domain.onSuccess
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.utils.Resource
import com.soethan.foodycompose.utils.Resource.Content
import com.soethan.foodycompose.utils.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    val foodRepo: FoodRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])


    private val _recipeDetailState = MutableStateFlow<Resource<RecipeEntity>>(Resource.Idle)
    val recipeDetailState: StateFlow<Resource<RecipeEntity>>
        get() = _recipeDetailState


    init {
        getRecipeInformation()
    }


    private fun getRecipeInformation() {
        viewModelScope.launch {
            _recipeDetailState.value = Resource.Loading

            val getRecipeInfo = foodRepo.getRecipeInformation(recipeId)
            val isFavRecipe = foodRepo.isFav(recipeId.toInt())
            getRecipeInfo.combine(isFavRecipe) { dataState, isFav ->
                dataState.onSuccess {
                    _recipeDetailState.value =
                        Content(it.copy(isFavorite = isFav))


                }.onError { code, message ->
                    _recipeDetailState.value =
                        Resource.Error(message = message ?: "Error Happened")

                }.onException { exception ->
                    _recipeDetailState.value =
                        Resource.Error(message = exception.message ?: "Error Happened")
                }
            }.launchIn(viewModelScope)
        }
    }


    fun toggleFavorite() {
        val currentRecipe = recipeDetailState.value.data()
        currentRecipe?.let { recipe ->
            when (recipe.isFavorite) {
                true -> removeFromFavorite()
                false -> addToFavorite()
            }
        }
    }


    private fun addToFavorite() {
        viewModelScope.launch {
            val currentRecipe = recipeDetailState.value.data()
            currentRecipe?.let {
                foodRepo.addToFav(it)
                _recipeDetailState.update {
                    Content(currentRecipe.copy(isFavorite = true))
                }
            }
        }
    }


    private fun removeFromFavorite() {
        viewModelScope.launch {
            foodRepo.removeFromFav(recipeId.toInt())
            val currentRecipe = recipeDetailState.value.data()
            currentRecipe?.let {
                _recipeDetailState.update {
                    Content(currentRecipe.copy(isFavorite = false))
                }

            }
        }
    }


}


data class RecipeType(
    val title: String,
    val isMet: Boolean
)