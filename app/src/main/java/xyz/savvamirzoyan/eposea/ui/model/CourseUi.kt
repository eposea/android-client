package xyz.savvamirzoyan.eposea.ui.model

import xyz.savvamirzoyan.eposea.core.Model

sealed class CourseUi : Model.Ui {
    data class Base(val id: String, val title: String, val teachers: String) : CourseUi()
    data class Error(val error: String) : CourseUi()
    object Loading : CourseUi()
}