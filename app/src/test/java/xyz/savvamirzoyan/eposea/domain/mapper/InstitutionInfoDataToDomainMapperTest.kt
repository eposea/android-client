package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoSectionData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoSectionItemData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoToolbarData
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoSectionDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoSectionItemDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoToolbarDomain

class InstitutionInfoDataToDomainMapperTest : TestCase() {

    private val mapper = InstitutionInfoDataToDomainMapper.Base()

    fun `test map with image`() {

        val input = InstitutionInfoData.Base(
            InstitutionInfoToolbarData("testImageUrl", "testTitle"),
            "testDescription",
            listOf(
                InstitutionInfoSectionData(
                    "testTitle0",
                    listOf(
                        InstitutionInfoSectionItemData("testId0", "testTitle0"),
                        InstitutionInfoSectionItemData("testId1", "testTitle1"),
                        InstitutionInfoSectionItemData("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionData(
                    "testTitle1",
                    listOf(
                        InstitutionInfoSectionItemData("testId0", "testTitle0"),
                        InstitutionInfoSectionItemData("testId1", "testTitle1"),
                        InstitutionInfoSectionItemData("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionData(
                    "testTitle2",
                    listOf(
                        InstitutionInfoSectionItemData("testId0", "testTitle0"),
                        InstitutionInfoSectionItemData("testId1", "testTitle1"),
                        InstitutionInfoSectionItemData("testId2", "testTitle2"),
                    )
                )
            )
        )
        val expected = InstitutionInfoDomain.Base(
            InstitutionInfoToolbarDomain("testImageUrl", "testTitle"),
            "testDescription",
            listOf(
                InstitutionInfoSectionDomain(
                    "testTitle0",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId1", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionDomain(
                    "testTitle1",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId1", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionDomain(
                    "testTitle2",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId1", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId2", "testTitle2"),
                    )
                ),
            )
        )

        assertEquals(expected, mapper.map(input))
    }

    fun `test map no image`() {

        val input = InstitutionInfoData.Base(
            InstitutionInfoToolbarData(null, "testTitle"),
            "testDescription",
            listOf(
                InstitutionInfoSectionData(
                    "testTitle0",
                    listOf(
                        InstitutionInfoSectionItemData("testId0", "testTitle0"),
                        InstitutionInfoSectionItemData("testId1", "testTitle1"),
                        InstitutionInfoSectionItemData("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionData(
                    "testTitle1",
                    listOf(
                        InstitutionInfoSectionItemData("testId0", "testTitle0"),
                        InstitutionInfoSectionItemData("testId1", "testTitle1"),
                        InstitutionInfoSectionItemData("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionData(
                    "testTitle2",
                    listOf(
                        InstitutionInfoSectionItemData("testId0", "testTitle0"),
                        InstitutionInfoSectionItemData("testId1", "testTitle1"),
                        InstitutionInfoSectionItemData("testId2", "testTitle2"),
                    )
                )
            )
        )
        val expected = InstitutionInfoDomain.Base(
            InstitutionInfoToolbarDomain("", "testTitle"),
            "testDescription",
            listOf(
                InstitutionInfoSectionDomain(
                    "testTitle0",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId1", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionDomain(
                    "testTitle1",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId1", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId2", "testTitle2"),
                    )
                ),
                InstitutionInfoSectionDomain(
                    "testTitle2",
                    listOf(
                        InstitutionInfoSectionItemDomain("testId0", "testTitle0"),
                        InstitutionInfoSectionItemDomain("testId1", "testTitle1"),
                        InstitutionInfoSectionItemDomain("testId2", "testTitle2"),
                    )
                ),
            )
        )

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error`() {

        val exception = NullPointerException()
        val input = InstitutionInfoData.Error(ErrorData.ApiError(exception, "errorMessage"))
        val expected = InstitutionInfoDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }
}