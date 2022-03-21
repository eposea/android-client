package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.domain.model.RegistrationDomain

class RegistrationDataToDomainMapperTest : TestCase() {

    private val mapper = RegistrationDataToDomainMapper.Base()

    fun `test map Success`() {

        val input = RegistrationData.Success("testTmpToken")
        val expected = RegistrationDomain.Base

        assertEquals(expected, mapper.map(input))
    }

    fun `test map ServerError`() {

        val input = RegistrationData.ServerError
        val expected = RegistrationDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map NotAuthorized`() {

        val input = RegistrationData.NotAuthorized
        val expected = RegistrationDomain.Error(R.string.not_authorized)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = RegistrationData.Error(ErrorData.ApiError(NullPointerException(), "errorMessage"))
        val input1 = RegistrationData.Error(ErrorData.ApiError(NullPointerException(), null))
        val expected = RegistrationDomain.Error(R.string.error_api)
        val expected1 = RegistrationDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
        assertEquals(expected1, mapper.map(input1))
    }

    fun `test map Error OtherError`() {

        val input = RegistrationData.Error(ErrorData.OtherError(NullPointerException(), "errorMessage"))
        val input1 = RegistrationData.Error(ErrorData.OtherError(NullPointerException(), null))
        val expected = RegistrationDomain.Error(R.string.error_other)
        val expected1 = RegistrationDomain.Error(R.string.error_other)

        assertEquals(expected, mapper.map(input))
        assertEquals(expected1, mapper.map(input1))
    }
}