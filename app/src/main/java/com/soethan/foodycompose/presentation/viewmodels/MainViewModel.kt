package com.soethan.foodycompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.soethan.foodycompose.domain.repo.UserSettingRepo
import com.soethan.foodycompose.utils.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userSettingRepo: UserSettingRepo) :
    ViewModel() {
    val appThemeState
        get()
        = userSettingRepo.appThemeState


    fun switchTheme(mode: AppTheme) {
        userSettingRepo.toggleAppTheme(mode)
    }

}