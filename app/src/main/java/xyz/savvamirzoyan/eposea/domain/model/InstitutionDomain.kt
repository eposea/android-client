package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain

sealed class InstitutionDomain : Model.Domain {

    data class Base(
        val id: String,
        val title: String,
        val courses: List<CourseDomain>
    ) : InstitutionDomain()

    data class Error(val error: ErrorDomain) : InstitutionDomain()
}
