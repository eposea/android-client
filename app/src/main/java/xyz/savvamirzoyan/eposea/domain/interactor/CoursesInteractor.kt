package xyz.savvamirzoyan.eposea.domain.interactor

import xyz.savvamirzoyan.eposea.data.repository.CoursesRepository
import xyz.savvamirzoyan.eposea.domain.mapper.CourseDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.model.CourseDomain

interface CoursesInteractor {

    suspend fun fetchCourses(): List<CourseDomain>

    class Base(
        private val coursesRepository: CoursesRepository,
        private val courseDataToDomainMapper: CourseDataToDomainMapper
    ) : CoursesInteractor {

        override suspend fun fetchCourses(): List<CourseDomain> {
            // TODO: Define logic of 'main' institution
            // for now it would be just the first university
            val coursesDomain = coursesRepository.fetchCourses("TEST-1")
            return courseDataToDomainMapper.map(coursesDomain)
        }
    }
}