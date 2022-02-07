package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.InstitutionDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.InstitutionWithCoursesUi

interface InstitutionDomainToWithCoursesUiMapper : Mapper<InstitutionDomain, InstitutionWithCoursesUi> {

    fun map(models: List<InstitutionDomain>): List<InstitutionWithCoursesUi>

    class Base(
        private val resourceManager: ResourceManager
    ) : InstitutionDomainToWithCoursesUiMapper {

        override fun map(models: List<InstitutionDomain>): List<InstitutionWithCoursesUi> {
            val result = mutableListOf<InstitutionWithCoursesUi>()

            models.forEach { institutionDomain ->
                when (institutionDomain) {
                    is InstitutionDomain.Base -> {

                        result.add(
                            InstitutionWithCoursesUi.Institution(institutionDomain.id, institutionDomain.title)
                        )

                        institutionDomain.courses.forEach { courseDomain ->
                            result.add(
                                InstitutionWithCoursesUi.Course(
                                    courseDomain.id,
                                    courseDomain.title,
                                    courseDomain.description
                                )
                            )
                        }
                    }
                    is InstitutionDomain.Error -> {
                        when (institutionDomain.error) {
                            is ErrorDomain.ApiError -> result.add(
                                InstitutionWithCoursesUi.Error(
                                    resourceManager.getString(
                                        R.string.error_api
                                    ),
                                    institutionDomain.error.errorMessage
                                )
                            )
                            is ErrorDomain.OtherError -> result.add(
                                InstitutionWithCoursesUi.Error(
                                    resourceManager.getString(
                                        R.string.error_other
                                    ),
                                    institutionDomain.error.errorMessage
                                )
                            )
                        }
                    }
                }
            }

            return result
        }
    }
}