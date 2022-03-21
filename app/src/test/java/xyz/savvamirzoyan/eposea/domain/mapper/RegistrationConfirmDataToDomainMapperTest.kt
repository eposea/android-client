package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.RegistrationConfirmDomain

class RegistrationConfirmDataToDomainMapperTest : TestCase() {

    private val errorDomainToDataMapper = ExceptionMapper.BaseErrorDataToDomainMapper()
    private val mapper = RegistrationConfirmDataToDomainMapper.Base(errorDomainToDataMapper)

    fun `test map Success`() {

        val input = RegistrationConfirmData.Success("testToken")
        val expected = RegistrationConfirmDomain.Success

        assertEquals(expected, mapper.map(input))
    }

    fun `test map NotAuthorized`() {

        val input = RegistrationConfirmData.NotAuthorized
        val expected = RegistrationConfirmDomain.NotAuthorized

        assertEquals(expected, mapper.map(input))
    }

    fun `test map ServerError`() {

        val input = RegistrationConfirmData.ServerError
        val expected = RegistrationConfirmDomain.ServerError

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val exception = NullPointerException()

        val input = RegistrationConfirmData.Error(ErrorData.ApiError(exception, "errorMessage"))
        val input1 = RegistrationConfirmData.Error(ErrorData.ApiError(exception, null))
        val expected = RegistrationConfirmDomain.Error(ErrorDomain.ApiError(exception, "errorMessage"))
        val expected1 =
            RegistrationConfirmDomain.Error(ErrorDomain.ApiError(exception, null))

        assertEquals(expected, mapper.map(input))
        assertEquals(expected1, mapper.map(input1))
    }

    fun `test map Error OtherError`() {

        val exception = NullPointerException()

        val input = RegistrationConfirmData.Error(ErrorData.OtherError(exception, "errorMessage"))
        val input1 = RegistrationConfirmData.Error(ErrorData.OtherError(exception, null))
        val expected = RegistrationConfirmDomain.Error(ErrorDomain.OtherError(exception, "errorMessage"))
        val expected1 =
            RegistrationConfirmDomain.Error(ErrorDomain.OtherError(exception, null))

        assertEquals(expected, mapper.map(input))
        assertEquals(expected1, mapper.map(input1))
    }
}