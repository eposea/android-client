package xyz.savvamirzoyan.eposea.domain.interactor

import kotlinx.coroutines.flow.*
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.repository.AuthRepository
import xyz.savvamirzoyan.eposea.domain.mapper.LoginDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.mapper.LoginDomainToAuthStatusDomainMapper
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.EditTextStatusDomain

interface LoginInteractor {

    val emailEditTextStateFlow: StateFlow<EditTextStatusDomain>
    val passwordEditTextStateFlow: StateFlow<EditTextStatusDomain>
    val isLoginButtonEnabledStateFlow: Flow<Boolean>
    val resultStatus: Flow<AuthStatusDomain>

    suspend fun onEmailChange(email: String)
    suspend fun onPasswordChange(password: String)
    suspend fun login(email: String, password: String)

    class Base(
        private val authRepository: AuthRepository,
        private val loginDataToDomainMapper: LoginDataToDomainMapper,
        private val loginDomainToAuthStatusDomainMapper: LoginDomainToAuthStatusDomainMapper
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

        private val _resultStatus = MutableSharedFlow<AuthStatusDomain>()
        override val resultStatus: Flow<AuthStatusDomain> = _resultStatus

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
            val loginDomain = loginDataToDomainMapper.map(authRepository.login(email, password))
            val authStatusDomain = loginDomainToAuthStatusDomainMapper.map(loginDomain)
            _resultStatus.emit(authStatusDomain)
        }
    }
}