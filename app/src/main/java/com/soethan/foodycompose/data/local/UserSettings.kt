package com.soethan.foodycompose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences


import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.utils.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_preferences")

interface UserSettings {

//    val themeStream: StateFlow<AppTheme>
//    var theme: AppTheme


    suspend fun setMealAndDiet(mealType: String, dietType: String)
    fun getMeal(): Flow<String>
    fun getDiet(): Flow<String>

    suspend fun setTheme(theme: AppTheme)
    fun getTheme(): Flow<AppTheme>


}