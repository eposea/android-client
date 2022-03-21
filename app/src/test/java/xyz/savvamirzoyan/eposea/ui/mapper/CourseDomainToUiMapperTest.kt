package xyz.savvamirzoyan.eposea.ui.mapper

import junit.framework.TestCase
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.model.CourseDomain
import xyz.savvamirzoyan.eposea.ui.model.CourseUi

class CourseDomainToUiMapperTest : TestCase() {

    private val resourceManager = ResourceManagerTest()
    private val mapper = CourseDomainToUiMapper.Base(resourceManager)

    fun `test map Base`() {

        val input = CourseDomain.Base(
            "testId",
            "testTitle",
            listOf("Teacher0", "Teacher1", "Teacher2")
        )
        val expected = CourseUi.Base(
            "testId",
            "testTitle",
            "Teacher0, Teacher1, Teacher2"
        )

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error ApiError`() {

        val input = CourseDomain.Error(R.string.error_api)
        val expected = CourseUi.Error("Server is unavailable\nPlease check connection with Internet or try again")

        assertEquals(expected, mapper.map(input))
    }

    fun `test map Error OtherError`() {

        val input = CourseDomain.Error(R.string.error_other)
        val expected = CourseUi.Error(resourceManager.getString(R.string.error_other))

        assertEquals(expected, mapper.map(input))
    }
}