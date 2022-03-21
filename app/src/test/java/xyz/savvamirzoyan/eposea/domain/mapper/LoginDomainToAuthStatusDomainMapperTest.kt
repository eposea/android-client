package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.LoginDomain

class LoginDomainToAuthStatusDomainMapperTest : TestCase() {

    private val mapper = LoginDomainToAuthStatusDomainMapper.Base()

    fun `test map Success`() {

        val input = LoginDomain.Success
        val expected = AuthStatusDomain.Success

        assertEquals(expected, mapper.map(input))
    }

    fun `test map NotAuthorized`() {

        val input = LoginDomain.NotAuthorized
        val expected = AuthStatusDomain.Fail(R.string.not_authorized)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error`() {

        val input = LoginDomain.Error(R.string.error_api)
        val input1 = LoginDomain.Error(R.string.error_other)
        val expected = AuthStatusDomain.Fail(R.string.error_api)

        assertEquals(expected, mapper.map(input))
        assertEquals(expected, mapper.map(input1))
    }
}