package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable

@Serializable
data class CoursesCloud(
    val courses: List<CourseCloud>
)
