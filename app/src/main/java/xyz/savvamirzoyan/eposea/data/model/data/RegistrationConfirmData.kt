package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class RegistrationConfirmData : Model.Data {
    data class Success(val token: String) : RegistrationConfirmData()
    data class Error(val error: ErrorData) : RegistrationConfirmData()
    object ServerError : RegistrationConfirmData()
    object NotAuthorized : RegistrationConfirmData()
}
