package com.soethan.foodycompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.DataState
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
import kotlinx.coroutines.flow.collect
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
        getRandomRecipes()
    }

    private fun getRandomRecipes() {


        viewModelScope.launch {
            _recipeListStateFlow.value = Resource.Loading
            foodRepo.getRandomRecipes().collectLatest { dataState ->
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


    private lateinit var mealAndDiet: MealAndDietType


    fun requestFilteredRecipe(mealAndDietType: MealAndDietType) {
        viewModelScope.launch(Dispatchers.IO) {
            userSettingRepo.writeMealAndDietType(mealAndDietType)

//            if (this@RecipeListViewModel::mealAndDiet.isInitialized) {
//            }
        }
    }

    fun saveMealAndDietTypeTemp(mealAndDietType: MealAndDietType) {
        this.mealAndDiet = mealAndDietType
    }


}