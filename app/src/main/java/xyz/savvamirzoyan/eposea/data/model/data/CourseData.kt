package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model

data class CourseData(
    val id: String,
    val title: String,
    val description: String
) : Model.Data
