package com.soethan.foodycompose.data.local

import com.soethan.foodycompose.utils.AppTheme
import kotlinx.coroutines.flow.StateFlow

interface UserSettings {

    val themeStream: StateFlow<AppTheme>
    var theme: AppTheme
}