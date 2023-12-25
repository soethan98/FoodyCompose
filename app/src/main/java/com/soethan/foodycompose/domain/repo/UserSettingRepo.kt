package com.soethan.foodycompose.domain.repo

import com.soethan.foodycompose.utils.AppTheme
import kotlinx.coroutines.flow.StateFlow

interface UserSettingRepo {

    val appThemeState: StateFlow<AppTheme>

     fun toggleAppTheme(mode:AppTheme)
}