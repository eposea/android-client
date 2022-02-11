package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoSectionData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoSectionItemData
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoSectionDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoSectionItemDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoToolbarDomain

interface InstitutionInfoDataToDomainMapper : Mapper<InstitutionInfoData, InstitutionInfoDomain> {

    fun map(model: InstitutionInfoData): InstitutionInfoDomain

    class Base : InstitutionInfoDataToDomainMapper {

        override fun map(model: InstitutionInfoData) = when (model) {
            is InstitutionInfoData.Base -> InstitutionInfoDomain.Base(
                toolbarInfo = InstitutionInfoToolbarDomain(model.toolbarInfo.imageUrl ?: "", model.toolbarInfo.title),
                description = model.description,
                sections = model.sections.map { map(it) })
            is InstitutionInfoData.Error -> {
                when (model.error) {
                    is ErrorData.ApiError -> InstitutionInfoDomain.Error(
                        ErrorDomain.ApiError(
                            model.error,
                            model.error.errorMessage
                        )
                    )
                    is ErrorData.NetworkError -> InstitutionInfoDomain.Error(
                        ErrorDomain.ApiError(
                            model.error,
                            model.error.errorMessage
                        )
                    )
                    is ErrorData.OtherError -> InstitutionInfoDomain.Error(
                        ErrorDomain.OtherError(
                            model.error,
                            model.error.errorMessage
                        )
                    )
                }
            }
        }

        private fun map(model: InstitutionInfoSectionData) = InstitutionInfoSectionDomain(
            title = model.title,
            items = model.items.map { map(it) }
        )

        private fun map(model: InstitutionInfoSectionItemData) = InstitutionInfoSectionItemDomain(
            id = model.id,
            title = model.title
        )
    }
}
