package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain

sealed class CourseDomain : Model.Domain {

    class Base(
        val id: String,
        val title: String,
        val teachers: List<String> // Maybe should be a list of objects)
    ) : CourseDomain()

    class Error(val error: ErrorDomain) : CourseDomain()
}
