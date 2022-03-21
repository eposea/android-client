package xyz.savvamirzoyan.eposea.ui.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoSectionDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoSectionItemDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoToolbarDomain
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoUi

class InstitutionInfoDomainToUiMapperTest : TestCase() {

    private val resourceManager = ResourceManagerTest()
    private val mapper = InstitutionInfoDomainToUiMapper.Base(resourceManager)

    fun `test map Base`() {
        val input = InstitutionInfoDomain.Base(
            InstitutionInfoToolbarDomain("testUrl", "testTitle"),
            "testDescription",
            listOf(
                InstitutionInfoSectionDomain(
                    "testTitle0",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId0", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId0", "testTitle2")
                    )
                ),
                InstitutionInfoSectionDomain(
                    "testTitle1",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId0", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId0", "testTitle2")
                    )
                ),
                InstitutionInfoSectionDomain(
                    "testTitle2",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId0", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId0", "testTitle2")
                    )
                )
            )
        )

        val expected = listOf(
            InstitutionInfoUi.Title(resourceManager.getString(R.string.description)),
            InstitutionInfoUi.Text("testDescription"),
            InstitutionInfoUi.Title("testTitle0"),
            InstitutionInfoUi.Text("testTitle0"),
            InstitutionInfoUi.Text("testTitle1"),
            InstitutionInfoUi.Text("testTitle2"),
            InstitutionInfoUi.Title("testTitle1"),
            InstitutionInfoUi.Text("testTitle0"),
            InstitutionInfoUi.Text("testTitle1"),
            InstitutionInfoUi.Text("testTitle2"),
            InstitutionInfoUi.Title("testTitle2"),
            InstitutionInfoUi.Text("testTitle0"),
            InstitutionInfoUi.Text("testTitle1"),
            InstitutionInfoUi.Text("testTitle2"),
        )

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = InstitutionInfoDomain.Error(R.string.error_api)
        val expected = listOf(InstitutionInfoUi.Error(resourceManager.getString(R.string.error_api)))

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {

        val input = InstitutionInfoDomain.Error(R.string.error_other)
        val expected = listOf(InstitutionInfoUi.Error(resourceManager.getString(R.string.error_other)))

        assertEquals(expected, mapper.map(input))
    }
}