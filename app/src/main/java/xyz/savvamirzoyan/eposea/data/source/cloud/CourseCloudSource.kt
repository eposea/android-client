package xyz.savvamirzoyan.eposea.data.source.cloud

import retrofit2.http.GET
import retrofit2.http.Path
import xyz.savvamirzoyan.eposea.data.model.cloud.CoursesCloud

interface CourseCloudSource {

    @GET("institutions/{institutionId}")
    suspend fun fetchCourses(@Path("institutionId") institutionId: String): CoursesCloud
}