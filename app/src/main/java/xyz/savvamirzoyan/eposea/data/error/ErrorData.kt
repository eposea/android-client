package xyz.savvamirzoyan.eposea.data.error

import xyz.savvamirzoyan.eposea.core.Error

sealed class ErrorData(exception: Exception, errorMessage: String?) : Error(exception, errorMessage) {
    class ApiError(exception: Exception, errorMessage: String?) : ErrorData(exception, errorMessage)
    class NetworkError(exception: Exception, errorMessage: String?) : ErrorData(exception, errorMessage)
    class OtherError(exception: Exception, errorMessage: String?) : ErrorData(exception, errorMessage)
}
