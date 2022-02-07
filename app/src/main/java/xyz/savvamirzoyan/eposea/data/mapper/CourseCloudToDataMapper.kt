package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.CourseCloud
import xyz.savvamirzoyan.eposea.data.model.data.CourseData

interface CourseCloudToDataMapper : Mapper<CourseCloud, CourseData> {

    fun map(model: CourseCloud): CourseData
    fun map(models: List<CourseCloud>): List<CourseData>

    class Base : CourseCloudToDataMapper {

        override fun map(model: CourseCloud) = CourseData(model.id, model.title, model.description)
        override fun map(models: List<CourseCloud>) = models.map { map(it) }
    }
}