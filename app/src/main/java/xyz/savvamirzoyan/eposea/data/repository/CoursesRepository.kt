package xyz.savvamirzoyan.eposea.data.repository

import xyz.savvamirzoyan.eposea.data.mapper.CourseCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.model.data.CourseData
import xyz.savvamirzoyan.eposea.data.source.cloud.CourseCloudSource

interface CoursesRepository {

    suspend fun fetchCourses(institutionId: String): List<CourseData>

    class Base(
        private val courseCloudSource: CourseCloudSource,
        private val courseCloudToDataMapper: CourseCloudToDataMapper
    ) : CoursesRepository {

        override suspend fun fetchCourses(institutionId: String): List<CourseData> = try {
            val coursesCloud = courseCloudSource.fetchCourses(institutionId)
            coursesCloud.courses.map { courseCloudToDataMapper.map(it) }
        } catch (e: Exception) {
            listOf(courseCloudToDataMapper.map(e))
        }
    }
}