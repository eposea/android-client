package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class CourseData : Model.Data {

    data class Base(
        val id: String,
        val title: String,
        val description: String
    ) : CourseData()

    data class Error(
        val error: ErrorData
    ) : CourseData()
}
