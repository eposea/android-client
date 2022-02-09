package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionData
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionDomain

interface InstitutionDataToDomainMapper : Mapper<InstitutionData, InstitutionDomain> {

    fun map(model: InstitutionData): InstitutionDomain
    fun map(models: List<InstitutionData>): List<InstitutionDomain>

    class Base(
        private val courseDataToDomainMapper: CourseDataToDomainMapper
    ) : InstitutionDataToDomainMapper {

        override fun map(model: InstitutionData) = when (model) {
            is InstitutionData.Base -> {
                InstitutionDomain.Base(model.id, model.title, model.imageUrl)
            }
            is InstitutionData.Error -> {
                when (model.error) {
                    is ErrorData.ApiError -> InstitutionDomain.Error(
                        ErrorDomain.ApiError(
                            model.error,
                            model.error.errorMessage
                        )
                    )
                    is ErrorData.NetworkError -> InstitutionDomain.Error(
                        ErrorDomain.ApiError(
                            model.error,
                            model.error.errorMessage
                        )
                    )
                    is ErrorData.OtherError -> InstitutionDomain.Error(
                        ErrorDomain.OtherError(
                            model.error,
                            model.error.errorMessage
                        )
                    )
                }
            }
        }

        override fun map(models: List<InstitutionData>) = models.map { map(it) }
    }
}