package xyz.savvamirzoyan.eposea.domain.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.CourseData
import xyz.savvamirzoyan.eposea.domain.model.CourseDomain

class CourseDataToDomainMapperTest : TestCase() {

    private val mapper = CourseDataToDomainMapper.Base()

    fun `test map Base`() {

        val input = CourseData.Base("testId", "testTitle", "testDescription")
        val expected = CourseDomain.Base("testId", "testTitle", listOf("No Teachers"))

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = CourseData.Error(ErrorData.ApiError(NullPointerException(), "errorMessage"))
        val expected = CourseDomain.Error(R.string.error_api)

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {

        val input = CourseData.Error(ErrorData.OtherError(NullPointerException(), "errorMessage"))
        val expected = CourseDomain.Error(R.string.error_other)

        assertEquals(expected, mapper.map(input))
    }
}