package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionInfoCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionInfoSectionCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionInfoSectionItemCloud
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoSectionData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoSectionItemData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoToolbarData

interface InstitutionInfoCloudToDataMapper : Mapper<InstitutionInfoCloud, InstitutionInfoData> {

    fun map(model: InstitutionInfoCloud): InstitutionInfoData
    fun map(model: InstitutionInfoSectionCloud): InstitutionInfoSectionData
    fun map(model: InstitutionInfoSectionItemCloud): InstitutionInfoSectionItemData
    fun map(exception: Exception): InstitutionInfoData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : InstitutionInfoCloudToDataMapper {

        override fun map(model: InstitutionInfoCloud): InstitutionInfoData = InstitutionInfoData.Base(
            toolbarInfo = InstitutionInfoToolbarData(model.imageUrl, model.title),
            description = model.description,
            sections = model.sections.map { map(it) }
        )

        override fun map(model: InstitutionInfoSectionCloud) = InstitutionInfoSectionData(
            title = model.title,
            items = model.items.map { map(it) }
        )

        override fun map(model: InstitutionInfoSectionItemCloud) = InstitutionInfoSectionItemData(
            id = model.id,
            title = model.title
        )

        override fun map(exception: Exception) =
            InstitutionInfoData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}
