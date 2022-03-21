package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.ui.mapper.ResourceManagerTest
import xyz.savvamirzoyan.eposea.ui.model.AuthStatusUi

class AuthStatusDomainToUiMapperTest : TestCase() {

    private val resourceManager = ResourceManagerTest()
    private val mapper = AuthStatusDomainToUiMapper.Base(resourceManager)

    fun `test map Success`() {

        val input = AuthStatusDomain.Success
        val expected = AuthStatusUi.Success

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Fail`() {

        val input = AuthStatusDomain.Fail(R.string.error_api)
        val expected = AuthStatusUi.Fail(resourceManager.getString(R.string.error_api))

        assertEquals(expected, mapper.map(input))
    }
}