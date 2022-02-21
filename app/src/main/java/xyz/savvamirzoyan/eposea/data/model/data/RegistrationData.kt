package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model

sealed class RegistrationData : Model.Data {

    class Base(val tmpToken: String) : RegistrationData()
    class Error(val errorCode: Int, val message: String) : RegistrationData()
    class Exception(val exception: kotlin.Exception) : RegistrationData()
}
