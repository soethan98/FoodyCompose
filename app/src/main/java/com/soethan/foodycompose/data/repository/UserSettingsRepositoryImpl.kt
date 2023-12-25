package com.soethan.foodycompose.data.repository

import com.soethan.foodycompose.data.local.UserSettings
import com.soethan.foodycompose.domain.repo.UserSettingRepo
import com.soethan.foodycompose.utils.AppTheme
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserSettingsRepositoryImpl @Inject constructor(
    private val userSettings: UserSettings,
) : UserSettingRepo {
    override val appThemeState: StateFlow<AppTheme>
        get() = userSettings.themeStream

    override fun toggleAppTheme(mode: AppTheme) {
        userSettings.theme = mode
    }

}