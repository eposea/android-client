package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.domain.interactor.LoginInteractor
import xyz.savvamirzoyan.eposea.domain.mapper.AuthStatusDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.mapper.EditTextStatusDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.model.EditTextStatusUi

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val editTextStatusDomainToUiMapper: EditTextStatusDomainToUiMapper,
    private val authStatusDomainToUiMapper: AuthStatusDomainToUiMapper
) : CoreViewModel() {

    private var email = ""
    private var password = ""

    val emailEditTextFlow: Flow<EditTextStatusUi> = loginInteractor.emailEditTextStateFlow
        .map { editTextStatusDomainToUiMapper.map(it) }

    val passwordEditTextFlow: Flow<EditTextStatusUi> = loginInteractor.passwordEditTextStateFlow
        .map { editTextStatusDomainToUiMapper.map(it) }

    val isLoginButtonEnabledFlow: Flow<Boolean> = loginInteractor.isLoginButtonEnabledStateFlow

    val authStatusFlow = loginInteractor.resultStatus
        .map { authStatusDomainToUiMapper.map(it) }

    fun onEmailChange(newEmail: String) {
        email = newEmail
        viewModelScope.launch { loginInteractor.onEmailChange(email) }
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        viewModelScope.launch { loginInteractor.onPasswordChange(password) }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            loginInteractor.login(email, password)
        }
    }
}
