package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.data.CourseData
import xyz.savvamirzoyan.eposea.domain.model.CourseDomain

interface CourseDataToDomainMapper : Mapper<CourseData, CourseDomain> {

    fun map(model: CourseData): CourseDomain
    fun map(models: List<CourseData>): List<CourseDomain>

    class Base : CourseDataToDomainMapper {

        override fun map(model: CourseData) = CourseDomain(model.id, model.title, model.description)
        override fun map(models: List<CourseData>) = models.map { map(it) }
    }
}