package xyz.savvamirzoyan.eposea.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.repository.AuthRepository
import xyz.savvamirzoyan.eposea.domain.model.EditTextStatusDomain

interface LoginInteractor {

    val emailEditTextStateFlow: StateFlow<EditTextStatusDomain>
    val passwordEditTextStateFlow: StateFlow<EditTextStatusDomain>
    val isLoginButtonEnabledStateFlow: Flow<Boolean>

    suspend fun onEmailChange(email: String)
    suspend fun onPasswordChange(password: String)
    suspend fun login(email: String, password: String)

    class Base(
        private val authRepository: AuthRepository
    ) : LoginInteractor {

        private val standardEmailEditTextStatus = EditTextStatusDomain()
        private val errorEmailEditTextStatus = EditTextStatusDomain(error = R.string.email_field_error)
        private val standardPasswordEditTextStatus = EditTextStatusDomain()
        private val errorPasswordEditTextStatus = EditTextStatusDomain(error = R.string.password_field_error)

        private val _emailEditTextStateFlow = MutableStateFlow(standardEmailEditTextStatus)
        override val emailEditTextStateFlow: StateFlow<EditTextStatusDomain> = _emailEditTextStateFlow

        private val _passwordEditTextStateFlow = MutableStateFlow(EditTextStatusDomain())
        override val passwordEditTextStateFlow: StateFlow<EditTextStatusDomain> = _passwordEditTextStateFlow

        private val _isEmailValidFlow = MutableStateFlow(false)
        private val _isPasswordValidFlow = MutableStateFlow(false)
        override val isLoginButtonEnabledStateFlow: Flow<Boolean> = _isEmailValidFlow
            .combine(_isPasswordValidFlow) { isEmailValid, isPasswordValid ->
                isEmailValid && isPasswordValid
            }

        override suspend fun onEmailChange(email: String) {

            val isEmailValid = authRepository.isEmailValid(email)
            _isEmailValidFlow.emit(isEmailValid)

            val valueToEmit = if (isEmailValid) standardEmailEditTextStatus else errorEmailEditTextStatus
            _emailEditTextStateFlow.emit(valueToEmit)
        }

        override suspend fun onPasswordChange(password: String) {
            val isPasswordValid = authRepository.isPasswordValid(password)
            _isPasswordValidFlow.emit(isPasswordValid)

            val valueToEmit = if (isPasswordValid) standardPasswordEditTextStatus else errorPasswordEditTextStatus
            _passwordEditTextStateFlow.emit(valueToEmit)
        }

        override suspend fun login(email: String, password: String) {
            authRepository.login(email, password)
        }
    }
}