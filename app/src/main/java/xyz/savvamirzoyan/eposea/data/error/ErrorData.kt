package xyz.savvamirzoyan.eposea.data.error

import xyz.savvamirzoyan.eposea.core.Error

sealed class ErrorData(exception: Exception) : Error(exception) {
    class ApiError(exception: Exception) : ErrorData(exception)
    class NetworkError(exception: Exception) : ErrorData(exception)
    class OtherError(exception: Exception) : ErrorData(exception)
}
