package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class RegistrationData : Model.Data {

    class Base(val tmpToken: String) : RegistrationData()
    class Error(val error: ErrorData) : RegistrationData()
}
