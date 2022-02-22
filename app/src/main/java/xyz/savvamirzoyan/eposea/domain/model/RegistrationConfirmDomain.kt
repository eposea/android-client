package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class RegistrationConfirmDomain : Model.Domain {

    object Success : RegistrationConfirmDomain()
    data class Error(val errorMessage: String) : RegistrationConfirmDomain()
}
