package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class RegistrationData : Model.Data {
    data class Success(val tmpToken: String) : RegistrationData()
    data class Error(val error: ErrorData) : RegistrationData()
    object NotAuthorized : RegistrationData()
    object ServerError : RegistrationData()
}
