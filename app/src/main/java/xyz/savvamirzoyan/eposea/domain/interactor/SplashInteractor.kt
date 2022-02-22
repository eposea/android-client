package xyz.savvamirzoyan.eposea.domain.interactor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.savvamirzoyan.eposea.data.repository.AuthRepository

interface SplashInteractor {

    val isLoggedInStateFlow: StateFlow<Boolean?>

    suspend fun checkToken()

    class Base(
        private val authRepository: AuthRepository
    ) : SplashInteractor {

        private val _isLoggedInStateFlow = MutableStateFlow<Boolean?>(null)
        override val isLoggedInStateFlow: StateFlow<Boolean?> = _isLoggedInStateFlow

        override suspend fun checkToken() {
            val value = authRepository.checkToken()
            _isLoggedInStateFlow.emit(value)
        }
    }
}