package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain

sealed class RegistrationConfirmDomain : Model.Domain {

    object Success : RegistrationConfirmDomain()
    data class Error(val error: ErrorDomain) : RegistrationConfirmDomain()
}
