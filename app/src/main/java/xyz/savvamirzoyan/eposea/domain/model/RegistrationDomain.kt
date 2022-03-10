package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain

sealed class RegistrationDomain : Model.Domain {

    object Base : RegistrationDomain()
    data class Error(val error: ErrorDomain) : RegistrationDomain()
}
