package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.domain.interactor.RegisterInteractor
import xyz.savvamirzoyan.eposea.extension.mapVisibility
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.mapper.EditTextStatusDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.model.EditTextStatusUi

class RegisterViewModel(
    private val registerInteractor: RegisterInteractor,
    private val editTextStatusDomainToUiMapper: EditTextStatusDomainToUiMapper,
    private val resourceManager: ResourceManager
) : CoreViewModel() {

    private var email = ""
    private var codeFromEmail = ""
    private var password = ""
    private var passwordRepeat = ""

    private val _isSendConfirmationCodeButtonVisible = MutableStateFlow(false)
    val isSendConfirmationCodeButtonVisible: StateFlow<Boolean> = _isSendConfirmationCodeButtonVisible

    val emailEditTextStateFlow: Flow<EditTextStatusUi> = registerInteractor.emailStatusFlow
        .map { editTextStatusDomainToUiMapper.map(it) }

    val passwordEditTextStateFlow: Flow<EditTextStatusUi> = registerInteractor.passwordStatusFlow
        .map { editTextStatusDomainToUiMapper.map(it) }

    val passwordRepeatEditTextStateFlow: Flow<EditTextStatusUi> = registerInteractor.passwordRepeatStatusFlow
        .map { editTextStatusDomainToUiMapper.map(it) }

    val isSignUpButtonEnabled: StateFlow<Boolean> = registerInteractor.isSignUpButtonEnabledFlow

    val isSignUpButtonVisible: Flow<Int> = registerInteractor.isSignUpButtonVisibleFlow
        .mapVisibility()

    val isConfirmationCodeBlockVisible: Flow<Int> = registerInteractor.isConfirmationBlockVisibleFlow
        .mapVisibility()

    val isProgressSignUpVisibleFlow: Flow<Int> = registerInteractor.isProgressSignUpVisibleFlow
        .mapVisibility()

    val errorMessageFlow: Flow<String> = registerInteractor.errorMessageIdFlow
        .map { resourceManager.getString(it) }

    private val _isSendCodeButtonEnabledFlow = MutableStateFlow(false)
    val isSendCodeButtonEnabledFlow: StateFlow<Boolean> = _isSendCodeButtonEnabledFlow

    fun onEmailChange(newEmail: String) {
        email = newEmail
        validateCredentials()
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        validateCredentials()
    }

    fun onPasswordRepeatChange(newPasswordRepeat: String) {
        passwordRepeat = newPasswordRepeat
        validateCredentials()
    }

    fun onCodeFromEmailChange(newCode: String) {
        codeFromEmail = newCode
        viewModelScope.launch {
            _isSendCodeButtonEnabledFlow.emit(codeFromEmail.isNotEmpty())
        }
    }

    private fun validateCredentials() {
        viewModelScope.launch {
            registerInteractor.validateCredentials(email, password, passwordRepeat)
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            registerInteractor.signUp(email, password)
        }
    }

    fun onSendCodeClick() {
        viewModelScope.launch {
            registerInteractor.sendVerificationCode(codeFromEmail)
        }
    }
}