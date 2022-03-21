package xyz.savvamirzoyan.eposea.domain.error

import xyz.savvamirzoyan.eposea.core.Error

sealed class ErrorDomain(exception: Exception, errorMessage: String?) : Error(exception, errorMessage) {
    class ApiError(exception: Exception, errorMessage: String?) : ErrorDomain(exception, errorMessage)
    class OtherError(exception: Exception, errorMessage: String?) : ErrorDomain(exception, errorMessage)

    override fun equals(other: Any?): Boolean {
        if (other is ErrorDomain)
            return this.exception == other.exception && this.errorMessage == other.errorMessage
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
