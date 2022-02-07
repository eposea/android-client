package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class CourseCloud(
    val id: String,
    val title: String,
    val description: String
) : Model.Cloud
