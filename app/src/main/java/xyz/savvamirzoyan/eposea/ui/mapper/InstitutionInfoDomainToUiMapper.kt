package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoUi

interface InstitutionInfoDomainToUiMapper : Mapper<InstitutionInfoDomain, InstitutionInfoUi> {

    fun map(model: InstitutionInfoDomain): List<InstitutionInfoUi>

    class Base(
        private val resourceManager: ResourceManager
    ) : InstitutionInfoDomainToUiMapper {

        override fun map(model: InstitutionInfoDomain) = when (model) {
            is InstitutionInfoDomain.Base -> {
                val result = mutableListOf<InstitutionInfoUi>()

                // Description static title
                result.add(InstitutionInfoUi.Title(resourceManager.getString(R.string.description)))

                // Description text
                result.add(
                    InstitutionInfoUi.Text(text = model.description)
                )

                // Sections
                model.sections.forEach { section ->
                    result.add(InstitutionInfoUi.Title(section.title))

                    section.items.forEach { item ->
                        result.add(InstitutionInfoUi.Text(item.title))
                    }
                }

                result
            }
            is InstitutionInfoDomain.Error -> listOf(
                when (model.error) {
                    is ErrorDomain.ApiError -> InstitutionInfoUi.Error(
                        resourceManager.getString(R.string.error_api),
                        model.error.errorMessage
                    )
                    is ErrorDomain.OtherError -> InstitutionInfoUi.Error(
                        resourceManager.getString(R.string.error_other),
                        model.error.errorMessage
                    )
                }
            )

        }
    }
}
