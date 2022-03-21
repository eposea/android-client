package xyz.savvamirzoyan.eposea.ui.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.model.InstitutionDomain
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi

class InstitutionDomainToUiMapperTest : TestCase() {

    private val resourceManager = ResourceManagerTest()
    private val mapper = InstitutionDomainToUiMapper.Base(resourceManager)

    fun `test map Base no image`() {

        val input = InstitutionDomain.Base("testId", "testTitle", "testUrl")
        val expected = InstitutionUi.Base("testId", "testTitle", "testUrl")

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Base with image`() {

        val input = InstitutionDomain.Base("testId", "test Title Value", null)
        val expected = InstitutionUi.BaseNoImage("testId", "test Title Value", "tTV")

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = InstitutionDomain.Error(R.string.error_api)
        val expected = InstitutionUi.Error(resourceManager.getString(R.string.error_api))

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {

        val input = InstitutionDomain.Error(R.string.error_other)
        val expected = InstitutionUi.Error(resourceManager.getString(R.string.error_other))

        assertEquals(expected, mapper.map(input))
    }
}