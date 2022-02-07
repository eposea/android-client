package xyz.savvamirzoyan.eposea.domain.error

import xyz.savvamirzoyan.eposea.core.Error

sealed class ErrorDomain(exception: Exception) : Error(exception) {
    class ApiError(exception: Exception) : ErrorDomain(exception)
    class OtherError(exception: Exception) : ErrorDomain(exception)
}
