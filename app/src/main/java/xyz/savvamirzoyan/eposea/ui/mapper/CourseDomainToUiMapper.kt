package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.CourseDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.CourseUi

interface CourseDomainToUiMapper : Mapper<CourseDomain, CourseUi> {

    fun map(model: CourseDomain): CourseUi

    class Base(
        private val resourceManager: ResourceManager
    ) : CourseDomainToUiMapper {

        override fun map(model: CourseDomain) = when (model) {
            is CourseDomain.Base -> CourseUi.Base(
                id = model.id,
                title = model.title,
                teachers = model.teachers.joinToString(", ")
            )
            is CourseDomain.Error -> when (model.error) {
                is ErrorDomain.ApiError -> CourseUi.Error(
                    resourceManager.getString(R.string.error_api),
                    model.error.errorMessage
                )
                is ErrorDomain.OtherError -> CourseUi.Error(
                    resourceManager.getString(R.string.error_other),
                    model.error.errorMessage
                )
            }
        }
    }
}
