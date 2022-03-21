package xyz.savvamirzoyan.eposea.domain.model

import androidx.annotation.StringRes
import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionDomain : Model.Domain {

    data class Base(
        val id: String,
        val title: String,
        val imageUrl: String?
    ) : InstitutionDomain()

    data class Error(@StringRes val message: Int) : InstitutionDomain()
}
