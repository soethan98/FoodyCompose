package com.soethan.foodycompose.domain.repo

import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.utils.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserSettingRepo {

    val appThemeState: Flow<AppTheme>

    suspend fun toggleAppTheme(mode: AppTheme)
    suspend fun writeMealAndDietType(mealAndDietType: MealAndDietType)
    fun retrieveMealAndDietType(): Flow<MealAndDietType>
}