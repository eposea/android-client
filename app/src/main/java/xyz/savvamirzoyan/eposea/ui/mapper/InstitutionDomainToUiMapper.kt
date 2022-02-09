package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi

interface InstitutionDomainToUiMapper : Mapper<InstitutionDomain, InstitutionUi> {

    fun map(model: InstitutionDomain): InstitutionUi
    fun map(models: List<InstitutionDomain>): List<InstitutionUi>

    class Base(
        private val resourceManager: ResourceManager
    ) : InstitutionDomainToUiMapper {

        override fun map(model: InstitutionDomain) = when (model) {
            is InstitutionDomain.Base -> InstitutionUi.Base(
                model.id,
                model.title,
                model.imageUrl,
                "${(0..50).random()}/${(50..100).random()}", // TODO: Replace with resources
                (1..5).random().toString()
            )
            is InstitutionDomain.Error -> {
                when (model.error) {
                    is ErrorDomain.ApiError -> InstitutionUi.Error(
                        resourceManager.getString(R.string.error_api),
                        model.error.errorMessage
                    )
                    is ErrorDomain.OtherError -> InstitutionUi.Error(
                        resourceManager.getString(R.string.error_other),
                        model.error.errorMessage
                    )
                }
            }
        }

        override fun map(models: List<InstitutionDomain>) = models.map { map(it) }
    }
}