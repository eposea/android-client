package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.CourseCloud
import xyz.savvamirzoyan.eposea.data.model.data.CourseData

interface CourseCloudToDataMapper : Mapper<CourseCloud, CourseData> {

    fun map(model: CourseCloud): CourseData
    fun map(exception: Exception): CourseData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : CourseCloudToDataMapper {

        override fun map(model: CourseCloud) = CourseData.Base(model.id, model.title, "model.description")
        override fun map(exception: Exception) = CourseData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}