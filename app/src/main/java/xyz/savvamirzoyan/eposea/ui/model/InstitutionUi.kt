package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionUi : Model.Ui {
    data class Base(
        val id: String,
        val title: String,
        val imageUrl: String,
    ) : InstitutionUi()

    data class BaseNoImage(
        val id: String,
        val title: String,
        val initials: String
    ) : InstitutionUi()

    data class Error(val error: String) : InstitutionUi()
    object Loading : InstitutionUi()
}
