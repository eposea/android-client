package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class LoginData : Model.Domain {
    object Success : LoginData()
    object NotAuthorized : LoginData()
    object ServerError : LoginData()
    data class Error(val error: ErrorData) : LoginData()
}
