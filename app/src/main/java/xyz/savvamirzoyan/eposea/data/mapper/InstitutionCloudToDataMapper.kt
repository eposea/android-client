package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionCloud
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionData

interface InstitutionCloudToDataMapper : Mapper<InstitutionCloud, InstitutionData> {

    fun map(model: InstitutionCloud): InstitutionData
    fun map(models: List<InstitutionCloud>): List<InstitutionData>
    fun map(exception: Exception): InstitutionData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : InstitutionCloudToDataMapper {

        override fun map(model: InstitutionCloud) = InstitutionData.Base(model.id, model.title, model.imageUrl)
        override fun map(models: List<InstitutionCloud>) = models.map { map(it) }
        override fun map(exception: Exception) =
            InstitutionData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}