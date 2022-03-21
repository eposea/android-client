package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.CourseData
import xyz.savvamirzoyan.eposea.domain.model.CourseDomain

interface CourseDataToDomainMapper : Mapper<CourseData, CourseDomain> {

    fun map(model: CourseData): CourseDomain
    fun map(models: List<CourseData>): List<CourseDomain>

    class Base : CourseDataToDomainMapper {

        override fun map(model: CourseData) = when (model) {
            is CourseData.Base -> CourseDomain.Base(model.id, model.title, listOf("No Teachers"))
            is CourseData.Error -> when (model.error) {
                is ErrorData.OtherError -> CourseDomain.Error(R.string.error_other)
                else -> CourseDomain.Error(R.string.error_api)
            }
        }

        override fun map(models: List<CourseData>) = models.map { map(it) }
    }
}