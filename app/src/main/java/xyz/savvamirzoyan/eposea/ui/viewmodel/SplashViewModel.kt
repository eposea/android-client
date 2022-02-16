package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel : CoreViewModel() {

    private val _isLoggedInStateFlow = MutableStateFlow<Boolean?>(null)
    val isLoggedInSharedFlow: StateFlow<Boolean?> = _isLoggedInStateFlow

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoggedInStateFlow.emit(false)
        }
    }
}