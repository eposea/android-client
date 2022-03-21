package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
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
            is InstitutionDomain.Base -> {
                if (model.imageUrl != null) {
                    InstitutionUi.Base(
                        model.id,
                        model.title,
                        model.imageUrl
                    )
                } else {
                    InstitutionUi.BaseNoImage(
                        model.id,
                        model.title,
                        model.title.split(" ").map { it.first() }.joinToString("")
                    )
                }
            }
            is InstitutionDomain.Error -> {
                InstitutionUi.Error(resourceManager.getString(model.message))
            }
        }

        override fun map(models: List<InstitutionDomain>) = models.map { map(it) }
    }
}