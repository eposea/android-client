package xyz.savvamirzoyan.eposea.ui.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.domain.model.EditTextStatusDomain
import xyz.savvamirzoyan.eposea.ui.model.EditTextStatusUi

class EditTextStatusDomainToUiMapperTest : TestCase() {

    private val resourceManager = ResourceManagerTest()
    private val mapper = EditTextStatusDomainToUiMapper.Base(resourceManager)

    fun `test map`() {

        val input = EditTextStatusDomain(0)
        val expected = EditTextStatusUi("0")

        assertEquals(expected, mapper.map(input))
    }
}