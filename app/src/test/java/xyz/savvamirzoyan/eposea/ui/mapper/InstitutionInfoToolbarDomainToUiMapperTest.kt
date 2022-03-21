package xyz.savvamirzoyan.eposea.ui.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoToolbarDomain
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoToolbarUi

class InstitutionInfoToolbarDomainToUiMapperTest : TestCase() {

    private val resourceManager = ResourceManagerTest()
    private val mapper = InstitutionInfoToolbarDomainToUiMapper.Base(resourceManager)

    fun `test map Base`() {

        val toolbar = InstitutionInfoToolbarDomain("testUrl", "testTitle")
        val input = InstitutionInfoDomain.Base(toolbar, "", listOf())
        val expected = InstitutionInfoToolbarUi.Base("testTitle", "testUrl")

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error`() {

        val input = InstitutionInfoDomain.Error(R.string.error)
        val expected = InstitutionInfoToolbarUi.Error(resourceManager.getString(R.string.error))

        assertEquals(expected, mapper.map(input))
    }
}