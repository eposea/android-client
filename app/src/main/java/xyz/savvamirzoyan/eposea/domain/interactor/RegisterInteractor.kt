package xyz.savvamirzoyan.eposea.domain.interactor

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.repository.AuthRepository
import xyz.savvamirzoyan.eposea.domain.mapper.RegistrationConfirmDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.mapper.RegistrationConfirmDomainToAuthStatusDomainMapper
import xyz.savvamirzoyan.eposea.domain.mapper.RegistrationDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.EditTextStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.RegistrationConfirmDomain
import xyz.savvamirzoyan.eposea.domain.model.RegistrationDomain

interface RegisterInteractor {

    val emailStatusFlow: StateFlow<EditTextStatusDomain>
    val passwordStatusFlow: StateFlow<EditTextStatusDomain>
    val passwordRepeatStatusFlow: StateFlow<EditTextStatusDomain>
    val isSignUpButtonEnabledFlow: StateFlow<Boolean>
    val isConfirmationBlockVisibleFlow: StateFlow<Boolean>
    val isSignUpButtonVisibleFlow: StateFlow<Boolean>
    val isProgressSignUpVisibleFlow: StateFlow<Boolean>
    val errorMessageIdFlow: SharedFlow<Int>
    val resultStatusFlow: SharedFlow<AuthStatusDomain>

    suspend fun validateCredentials(email: String, password: String, passwordRepeat: String)
    suspend fun signUp(email: String, password: String)
    suspend fun sendVerificationCode(verificationCode: String)

    class Base(
        private val authRepository: AuthRepository,
        private val registrationDataToDomainMapper: RegistrationDataToDomainMapper,
        private val registrationConfirmDataToDomainMapper: RegistrationConfirmDataToDomainMapper,
        private val registrationConfirmDomainToAuthStatusDomainMapper: RegistrationConfirmDomainToAuthStatusDomainMapper
    ) : RegisterInteractor {

        private val emailStandardStatus = EditTextStatusDomain()
        private val passwordStandardStatus = EditTextStatusDomain()
        private val passwordRepeatStandardStatus = EditTextStatusDomain()

        private val _emailStatusFlow = MutableStateFlow(emailStandardStatus)
        override val emailStatusFlow: StateFlow<EditTextStatusDomain> = _emailStatusFlow

        private val _passwordStatusFlow = MutableStateFlow(passwordStandardStatus)
        override val passwordStatusFlow: StateFlow<EditTextStatusDomain> = _passwordStatusFlow

        private val _passwordRepeatStatusFlow = MutableStateFlow(passwordRepeatStandardStatus)
        override val passwordRepeatStatusFlow: StateFlow<EditTextStatusDomain> = _passwordRepeatStatusFlow

        private val _isSignUpButtonEnabledFlow = MutableStateFlow(false)
        override val isSignUpButtonEnabledFlow: StateFlow<Boolean> = _isSignUpButtonEnabledFlow

        private val _isConfirmationBlockVisibleFlow = MutableStateFlow(false)
        override val isConfirmationBlockVisibleFlow: StateFlow<Boolean> = _isConfirmationBlockVisibleFlow

        private val _isSignUpButtonVisibleFlow = MutableStateFlow(true)
        override val isSignUpButtonVisibleFlow: StateFlow<Boolean> = _isSignUpButtonVisibleFlow

        private val _isProgressSignUpVisibleFlow = MutableStateFlow(false)
        override val isProgressSignUpVisibleFlow: StateFlow<Boolean> = _isProgressSignUpVisibleFlow

        private val _errorMessageIdFlow = MutableSharedFlow<Int>()
        override val errorMessageIdFlow: SharedFlow<Int> = _errorMessageIdFlow

        private val _resultStatusFlow = MutableSharedFlow<AuthStatusDomain>()
        override val resultStatusFlow: SharedFlow<AuthStatusDomain> = _resultStatusFlow

        override suspend fun validateCredentials(email: String, password: String, passwordRepeat: String) {

            val isEmailInvalid = !authRepository.isEmailValid(email)
            val isPasswordInvalid = !authRepository.isPasswordValid(password)
            val isPasswordRepeatInvalid = isPasswordRepeatInvalid(passwordRepeat, password)

            // Email
            if (isEmailInvalid) {
                _emailStatusFlow.emit(EditTextStatusDomain(error = R.string.email_field_error))
            } else {
                _emailStatusFlow.emit(emailStandardStatus)
            }

            // Password
            if (isPasswordInvalid) {
                _passwordStatusFlow.emit(EditTextStatusDomain(error = R.string.password_field_error))
            } else {
                _passwordStatusFlow.emit(passwordStandardStatus)
            }

            // Password repeat
            if (isPasswordRepeatInvalid) {
                _passwordRepeatStatusFlow.emit(EditTextStatusDomain(error = R.string.password_error_dont_match))
            } else {
                _passwordRepeatStatusFlow.emit(passwordRepeatStandardStatus)
            }

            _isConfirmationBlockVisibleFlow.emit(false)
            _isSignUpButtonVisibleFlow.emit(true)
            _isSignUpButtonEnabledFlow.emit(
                !isEmailInvalid
                        && !isPasswordInvalid
                        && !isPasswordRepeatInvalid
                        && password.isNotEmpty()
                        && passwordRepeat.isNotEmpty()
            )
        }

        override suspend fun signUp(email: String, password: String) {
            _isSignUpButtonVisibleFlow.emit(false)
            _isProgressSignUpVisibleFlow.emit(true)

            val registrationDomain: RegistrationDomain =
                registrationDataToDomainMapper.map(authRepository.sendRegisterCredentials(email, password))

            _isProgressSignUpVisibleFlow.emit(false)

            when (registrationDomain) {
                RegistrationDomain.Base -> {
                    _isConfirmationBlockVisibleFlow.emit(true)
                }

                is RegistrationDomain.Error -> {
                    _isSignUpButtonVisibleFlow.emit(true)
                    _errorMessageIdFlow.emit(registrationDomain.message)
                }
            }
        }

        override suspend fun sendVerificationCode(verificationCode: String) {
            val registrationConfirmDomain: RegistrationConfirmDomain = registrationConfirmDataToDomainMapper
                .map(authRepository.sendConfirmationCode(verificationCode))

            val authStatusDomain = registrationConfirmDomainToAuthStatusDomainMapper.map(registrationConfirmDomain)
            _resultStatusFlow.emit(authStatusDomain)
        }

        private fun isPasswordRepeatInvalid(passwordRepeat: String, password: String) =
            (passwordRepeat.isBlank() && passwordRepeat.isNotEmpty()) || (passwordRepeat.isNotEmpty() && passwordRepeat != password)
    }
}
