package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

sealed class CourseDomain : Model.Domain {

    data class Base(
        val id: String,
        val title: String,
        val teachers: List<String> // Maybe should be a list of objects)
    ) : CourseDomain()

    data class Error(@StringRes val message: Int) : CourseDomain()
}
