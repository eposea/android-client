package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.BuildConfig
import xyz.savvamirzoyan.eposea.domain.interactor.SplashInteractor

class SplashViewModel(
    private val splashInteractor: SplashInteractor
) : CoreViewModel() {

    val isLoggedInFlow = splashInteractor.isLoggedInStateFlow

    fun onStart() {
        viewModelScope.launch {

            if (BuildConfig.DEBUG)
                delay(1500)

            splashInteractor.checkToken()
        }
    }
}