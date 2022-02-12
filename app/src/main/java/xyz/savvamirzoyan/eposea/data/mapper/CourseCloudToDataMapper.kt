package xyz.savvamirzoyan.eposea.data.mapper

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.cloud.CourseCloud
import xyz.savvamirzoyan.eposea.data.model.data.CourseData
import java.net.UnknownHostException

interface CourseCloudToDataMapper : Mapper<CourseCloud, CourseData> {

    fun map(model: CourseCloud): CourseData
    fun map(exception: Exception): CourseData

    class Base : CourseCloudToDataMapper {

        override fun map(model: CourseCloud) = CourseData.Base(model.id, model.title, "model.description")
        override fun map(exception: Exception) = CourseData.Error(
            when (exception) {
                is UnknownHostException -> ErrorData.NetworkError(exception, exception.message ?: "")
                is HttpException -> ErrorData.NetworkError(exception, exception.message ?: "")
                is SerializationException -> ErrorData.ApiError(exception, exception.message ?: "")
                else -> ErrorData.OtherError(exception, exception.message ?: "")
            }
        )
    }
}