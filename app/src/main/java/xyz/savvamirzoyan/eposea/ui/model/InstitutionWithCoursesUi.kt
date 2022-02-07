package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class InstitutionWithCoursesUi : Model.Ui {
    data class Institution(val id: String, val title: String) : InstitutionWithCoursesUi()
    data class Course(val id: String, val title: String, val description: String) : InstitutionWithCoursesUi()
    data class Error(val error: String, val errorMessage: String) : InstitutionWithCoursesUi()
    object Loading : InstitutionWithCoursesUi()
}
