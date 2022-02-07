package xyz.savvamirzoyan.eposea.data.mapper

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionCloud
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionData
import java.net.UnknownHostException

interface InstitutionCloudToDataMapper : Mapper<InstitutionCloud, InstitutionData> {

    fun map(model: InstitutionCloud): InstitutionData
    fun map(models: List<InstitutionCloud>): List<InstitutionData>
    fun map(exception: Exception): InstitutionData

    class Base(
        private val courseCloudToDataMapper: CourseCloudToDataMapper
    ) : InstitutionCloudToDataMapper {

        override fun map(model: InstitutionCloud): InstitutionData {
            val courses = courseCloudToDataMapper.map(model.courses)
            return InstitutionData.Base(model.id, model.title, courses)
        }

        override fun map(models: List<InstitutionCloud>) = models.map { map(it) }
        override fun map(exception: Exception) = InstitutionData.Error(
            when (exception) {
                is UnknownHostException -> ErrorData.NetworkError(exception)
                is HttpException -> ErrorData.NetworkError(exception)
                is SerializationException -> ErrorData.ApiError(exception)
                else -> ErrorData.OtherError(exception)
            }
        )
    }
}