package com.soethan.foodycompose.data.repository

import com.soethan.foodycompose.data.local.UserSettings
import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.domain.repo.UserSettingRepo
import com.soethan.foodycompose.utils.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

class UserSettingsRepositoryImpl @Inject constructor(
    private val userSettings: UserSettings,
) : UserSettingRepo {
    override val appThemeState: Flow<AppTheme>
        get() = userSettings.getTheme()

    override suspend fun toggleAppTheme(mode: AppTheme) {
       userSettings.setTheme(mode)
    }

    override suspend fun writeMealAndDietType(mealAndDietType: MealAndDietType) {
        userSettings.setMealAndDiet(mealType = mealAndDietType.selectedMealType,
            dietType = mealAndDietType.selectedDietType)
    }

    override fun retrieveMealAndDietType(): Flow<MealAndDietType> {
       return  userSettings.getMeal().combine(userSettings.getDiet()) {a ,b ->
           MealAndDietType(selectedDietType = b, selectedMealType = a)
       }
    }


}