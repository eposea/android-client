package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

sealed class AuthStatusDomain : Model.Domain {
    object Success : AuthStatusDomain()
    data class Fail(@StringRes val message: Int) : AuthStatusDomain()
}