package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionData
import xyz.savvamirzoyan.eposea.domain.model.InstitutionDomain

class InstitutionDataToDomainMapperTest : TestCase() {

    private val mapper = InstitutionDataToDomainMapper.Base()

    fun `test map Base with image`() {

        val input = InstitutionData.Base("testId", "testTitle", "testImageUrl")
        val expected = InstitutionDomain.Base("testId", "testTitle", "testImageUrl")

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Base no image`() {

        val input = InstitutionData.Base("testId", "testTitle", null)
        val expected = InstitutionDomain.Base("testId", "testTitle", null)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = InstitutionData.Error(ErrorData.ApiError(NullPointerException(), "errorMessage"))
        val expected = InstitutionDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {

        val input = InstitutionData.Error(ErrorData.OtherError(NullPointerException(), "errorMessage"))
        val expected = InstitutionDomain.Error(R.string.error_other)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error NetworkError`() {

        val input = InstitutionData.Error(ErrorData.OtherError(NullPointerException(), "errorMessage"))
        val expected = InstitutionDomain.Error(R.string.error_other)

        assertEquals(expected, mapper.map(input))
    }
}