package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

sealed class RegistrationDomain : Model.Domain {

    object Base : RegistrationDomain()
    data class Error(@StringRes val message: Int) : RegistrationDomain()
}
