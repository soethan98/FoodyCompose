package com.soethan.foodycompose.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.utils.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class UserSettingsImpl @Inject constructor(@ApplicationContext val context: Context) :
    UserSettings {

    override fun getDiet(): Flow<String> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { pref ->
            pref[PreferencesKeys.DIET_TYPE] ?: ""
        }
    }

    override fun getMeal(): Flow<String> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { pref ->
            pref[PreferencesKeys.MEAL_TYPE] ?: ""
        }
    }


    override suspend fun setMealAndDiet(mealType: String, dietType: String) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.MEAL_TYPE] = mealType
            settings[PreferencesKeys.DIET_TYPE] = dietType
        }
    }

    override suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_THEME_MODE] = theme.name
        }
    }

    override fun getTheme(): Flow<AppTheme> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            AppTheme.valueOf(it[PreferencesKeys.APP_THEME_MODE] ?: AppTheme.MODE_AUTO.name)
        }
    }

    private object PreferencesKeys {
        val APP_THEME_MODE = stringPreferencesKey("app_theme_mode")
        val MEAL_TYPE = stringPreferencesKey("meal")
        val DIET_TYPE = stringPreferencesKey("diet")
    }

}