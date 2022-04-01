package xyz.savvamirzoyan.eposea.core

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import java.net.UnknownHostException

interface Mapper<T : Model, R : Model>

interface ExceptionMapper {

    interface ExceptionToErrorDataMapper : ExceptionMapper {
        fun mapException(exception: Exception): ErrorData
    }

    interface ErrorDataToDomainMapper : ExceptionMapper {
        fun mapError(errorData: ErrorData): ErrorDomain
    }

    class BaseExceptionToErrorDataMapper : ExceptionToErrorDataMapper {
        override fun mapException(exception: Exception): ErrorData = when (exception) {
            is UnknownHostException -> ErrorData.NetworkError(exception, exception.message)
            is HttpException -> ErrorData.NetworkError(exception, exception.message)
            is SerializationException -> ErrorData.ApiError(exception, exception.message)
            else -> ErrorData.OtherError(exception, exception.message)
        }
    }

    class BaseErrorDataToDomainMapper : ErrorDataToDomainMapper {
        override fun mapError(errorData: ErrorData): ErrorDomain = when (errorData) {
            is ErrorData.ApiError -> ErrorDomain.ApiError(errorData.exception, errorData.errorMessage)
            is ErrorData.NetworkError -> ErrorDomain.ApiError(errorData.exception, errorData.errorMessage)
            is ErrorData.OtherError -> ErrorDomain.OtherError(errorData.exception, errorData.errorMessage)
        }
    }
}