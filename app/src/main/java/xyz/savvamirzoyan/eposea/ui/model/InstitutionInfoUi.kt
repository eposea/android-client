package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionInfoUi : Model.Ui {
    data class Title(val text: String) : InstitutionInfoUi()
    data class Text(val text: String) : InstitutionInfoUi()
    data class Error(val error: String) : InstitutionInfoUi()
    object Loading : InstitutionInfoUi()
}