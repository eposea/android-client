package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.RegistrationConfirmDomain

class RegistrationConfirmDomainToAuthStatusDomainMapperTest : TestCase() {

    private val mapper = RegistrationConfirmDomainToAuthStatusDomainMapper.Base()

    fun `test map Success`() {

        val input = RegistrationConfirmDomain.Success
        val expected = AuthStatusDomain.Success

        assertEquals(expected, mapper.map(input))
    }

    fun `test map ServerError`() {

        val input = RegistrationConfirmDomain.ServerError
        val expected = AuthStatusDomain.Fail(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map NotAuthorized`() {

        val input = RegistrationConfirmDomain.NotAuthorized
        val expected = AuthStatusDomain.Fail(R.string.invalid_code_from_email)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = RegistrationConfirmDomain.Error(ErrorDomain.ApiError(NullPointerException(), "errorMessage"))
        val expected = AuthStatusDomain.Fail(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {

        val input = RegistrationConfirmDomain.Error(ErrorDomain.ApiError(NullPointerException(), "errorMessage"))
        val expected = AuthStatusDomain.Fail(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }
}