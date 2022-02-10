package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionUi : Model.Ui {
    data class Base(
        val id: String,
        val title: String,
        val imageUrl: String,
        val tasksDone: String,
        val urgentTasks: String
    ) : InstitutionUi()

    data class BaseNoImage(
        val id: String,
        val title: String,
        val capitalLetter: Char,
        val tasksDone: String,
        val urgentTasks: String
    ) : InstitutionUi()

    data class Error(val error: String, val errorMessage: String) : InstitutionUi()
    object Loading : InstitutionUi()
}
