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
//    override fun getMealAndDiet(): Flow<MealAndDietType> {
//       return  context.dataStore.data.catch { exception ->
//            if (exception is IOException) {
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }.map {
//            val meal = MealType.valueOf(it[PreferencesKeys.MEAL_TYPE] ?: MealType.MAIN_COURSE.value)
//            val diet = DietType.valueOf(it[PreferencesKeys.DIET_TYPE] ?: DietType.GLUTEN_FREE.value)
//            MealAndDietType(selectedDietType = diet, selectedMealType = meal)
//
//        }
//    }

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

//class UserSettingsImpl @Inject constructor(
//    @ApplicationContext context: Context
//) : UserSettings {
//
//
//
//    override val themeStream: MutableStateFlow<AppTheme>
//    override var theme: AppTheme by AppThemePreferenceDelegate("app_theme", AppTheme.MODE_AUTO)
//
//
//    private val preferences: SharedPreferences =
//        context.getSharedPreferences("sample_theme", Context.MODE_PRIVATE)
//
//    init {
//        "hello".reversed()
//        themeStream = MutableStateFlow(theme)
//    }
//
//    inner class AppThemePreferenceDelegate(
//        private val name: String,
//        private val default: AppTheme
//    ) : ReadWriteProperty<Any?, AppTheme> {
//
//
//        override fun getValue(thisRef: Any?, property: KProperty<*>): AppTheme =
//            AppTheme.fromOrdinal(preferences.getInt(name, default.ordinal))
//
//        override fun setValue(thisRef: Any?, property: KProperty<*>, value: AppTheme) {
//
//            themeStream.value = value
//            preferences.edit{
//                putInt(name,value.ordinal)
//            }
//        }
//    }
//}