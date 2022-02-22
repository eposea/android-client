package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.domain.interactor.SplashInteractor

class SplashViewModel(
    private val splashInteractor: SplashInteractor
) : CoreViewModel() {

    val isLoggedInSharedFlow: StateFlow<Boolean?> = splashInteractor.isLoggedInStateFlow

    fun onStart() {
        viewModelScope.launch {
            splashInteractor.checkToken()
        }
    }
}