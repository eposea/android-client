package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

sealed class LoginDomain : Model.Domain {
    object Success : LoginDomain()
    object NotAuthorized : LoginDomain()
    data class Error(@StringRes val message: Int) : LoginDomain()
}