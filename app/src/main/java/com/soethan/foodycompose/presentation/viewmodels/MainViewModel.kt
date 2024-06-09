package com.soethan.foodycompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.foodycompose.domain.repo.UserSettingRepo
import com.soethan.foodycompose.utils.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userSettingRepo: UserSettingRepo) :
    ViewModel() {
    val appThemeState
        get()
        = userSettingRepo.appThemeState.stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = AppTheme.MODE_AUTO)


    fun switchTheme(mode: AppTheme) {
        viewModelScope.launch {
            userSettingRepo.toggleAppTheme(mode)

        }
    }

}