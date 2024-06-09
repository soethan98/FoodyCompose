package com.soethan.foodycompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.onError
import com.soethan.foodycompose.domain.onException
import com.soethan.foodycompose.domain.onSuccess
import com.soethan.foodycompose.domain.repo.FoodRepo
import com.soethan.foodycompose.domain.repo.UserSettingRepo
import com.soethan.foodycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val foodRepo: FoodRepo,
    private val userSettingRepo: UserSettingRepo
) : ViewModel() {
    private val _recipeListStateFlow = MutableStateFlow<Resource<List<RecipeEntity>>>(Resource.Idle)
    val recipeListStateFlow: StateFlow<Resource<List<RecipeEntity>>>
        get() = _recipeListStateFlow

    val readMealAndDietType = userSettingRepo.retrieveMealAndDietType().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = MealAndDietType()
    )


    init {
        getRecipes()
    }

    private fun getRecipes() {


        viewModelScope.launch {
            _recipeListStateFlow.value = Resource.Loading
            val currentMealAndDietType = readMealAndDietType.value
            foodRepo.getRandomRecipes(currentMealAndDietType).collectLatest { dataState ->
                dataState.onSuccess {
                    _recipeListStateFlow.value = Resource.Content(data = it)

                }.onError { code, message ->
                    _recipeListStateFlow.value =
                        Resource.Error(message = message ?: "Error Happened")

                }.onException {
                    _recipeListStateFlow.value =
                        Resource.Error(message = it.message ?: "Error Happened")

                }

            }

        }
    }


    fun requestFilteredRecipe(mealAndDietType: MealAndDietType) {
        viewModelScope.launch(Dispatchers.IO) {
            userSettingRepo.writeMealAndDietType(mealAndDietType)
            getRecipes()
        }
    }


}