package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoToolbarUi

interface InstitutionInfoToolbarDomainToUiMapper : Mapper<InstitutionInfoDomain, InstitutionInfoToolbarUi> {

    fun map(model: InstitutionInfoDomain): InstitutionInfoToolbarUi
    class Base(private val resourceManager: ResourceManager) : InstitutionInfoToolbarDomainToUiMapper {

        override fun map(model: InstitutionInfoDomain) = when (model) {
            is InstitutionInfoDomain.Base -> InstitutionInfoToolbarUi.Base(
                imageUrl = model.toolbarInfo.imageUrl,
                title = model.toolbarInfo.title
            )
            is InstitutionInfoDomain.Error -> InstitutionInfoToolbarUi.Error(
                title = resourceManager.getString(R.string.error)
            )
        }
    }
}
