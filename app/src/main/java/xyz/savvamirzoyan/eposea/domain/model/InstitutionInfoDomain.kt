package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionInfoDomain : Model.Domain {

    data class Base(
        val toolbarInfo: InstitutionInfoToolbarDomain,
        val description: String,
        val sections: List<InstitutionInfoSectionDomain>
    ) : InstitutionInfoDomain()

    data class Error(@StringRes val message: Int) : InstitutionInfoDomain()
}
