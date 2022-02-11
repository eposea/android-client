package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain

sealed class InstitutionInfoDomain : Model.Domain {

    data class Base(
        val toolbarInfo: InstitutionInfoToolbarDomain,
        val description: String,
        val sections: List<InstitutionInfoSectionDomain>
    ) : InstitutionInfoDomain()

    data class Error(val error: ErrorDomain) : InstitutionInfoDomain()
}
