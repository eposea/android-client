package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.LoginData
import xyz.savvamirzoyan.eposea.domain.model.LoginDomain

class LoginDataToDomainMapperTest : TestCase() {

    private val mapper = LoginDataToDomainMapper.Base()

    fun `test map Success`() {

        val input = LoginData.Success
        val expected = LoginDomain.Success

        assertEquals(expected, mapper.map(input))
    }

    fun `test map NotAuthorized`() {

        val input = LoginData.NotAuthorized
        val expected = LoginDomain.NotAuthorized

        assertEquals(expected, mapper.map(input))
    }

    fun `test map ServerError`() {

        val input = LoginData.ServerError
        val expected = LoginDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = LoginData.Error(ErrorData.ApiError(NullPointerException(), "errorMessage"))
        val expected = LoginDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error NetworkError`() {
        val input = LoginData.Error(ErrorData.NetworkError(NullPointerException(), "errorMessage"))
        val expected = LoginDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {
        val input = LoginData.Error(ErrorData.OtherError(NullPointerException(), "errorMessage"))
        val expected = LoginDomain.Error(R.string.error_other)

        assertEquals(expected, mapper.map(input))
    }
}