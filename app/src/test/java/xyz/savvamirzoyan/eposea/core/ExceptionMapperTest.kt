package xyz.savvamirzoyan.eposea.core

import junit.framework.TestCase
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import java.net.UnknownHostException

class ExceptionMapperTest : TestCase() {

    private val exceptionToErrorDataMapper = ExceptionMapper.BaseExceptionToErrorDataMapper()
    private val errorDataToDomainMapper = ExceptionMapper.BaseErrorDataToDomainMapper()

    fun `test UnknownHostException to ErrorData`() {

        val input = UnknownHostException("https://eposea.com")
        val expected = ErrorData.NetworkError(input, "errorMessage")

        assertEquals(expected::class, exceptionToErrorDataMapper.mapException(input)::class)
    }

    fun `test HttpException to ErrorData`() {

        val input = HttpException(Response.success(null))
        val expected = ErrorData.NetworkError(input, "errorMessage")

        assertEquals(expected::class, exceptionToErrorDataMapper.mapException(input)::class)
    }

    fun `test SerializationException to ErrorData`() {

        val input = SerializationException()
        val expected = ErrorData.ApiError(input, "errorMessage")

        assertEquals(expected::class, exceptionToErrorDataMapper.mapException(input)::class)
    }

    fun `test General to ErrorData`() {

        val input = NullPointerException()
        val expected = ErrorData.OtherError(input, "errorMessage")

        assertEquals(expected::class, exceptionToErrorDataMapper.mapException(input)::class)
    }

    fun `test ErrorData NetworkError to ErrorDomain`() {

        val input = ErrorData.NetworkError(NullPointerException(), "errorMessage")
        val expected = ErrorDomain.ApiError(NullPointerException(), "errorMessage")

        assertEquals(expected::class, errorDataToDomainMapper.mapError(input)::class)
    }

    fun `test ErrorData ApiError to ErrorDomain`() {

        val input = ErrorData.ApiError(NullPointerException(), "errorMessage")
        val expected = ErrorDomain.ApiError(NullPointerException(), "errorMessage")

        assertEquals(expected::class, errorDataToDomainMapper.mapError(input)::class)
    }

    fun `test ErrorData OtherError to ErrorDomain`() {

        val input = ErrorData.OtherError(NullPointerException(), "errorMessage")
        val expected = ErrorDomain.OtherError(NullPointerException(), "errorMessage")

        assertEquals(expected::class, errorDataToDomainMapper.mapError(input)::class)
    }
}