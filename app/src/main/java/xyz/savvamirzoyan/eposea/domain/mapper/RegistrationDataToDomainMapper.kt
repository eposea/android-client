package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.RegistrationDomain

interface RegistrationDataToDomainMapper : Mapper<RegistrationData, RegistrationDomain> {

    fun map(model: RegistrationData): RegistrationDomain

    class Base : RegistrationDataToDomainMapper {

        override fun map(model: RegistrationData) = when (model) {
            is RegistrationData.Base -> RegistrationDomain.Base
            is RegistrationData.Error -> RegistrationDomain.Error(
                ErrorDomain.ApiError(RuntimeException(), model.message)
            )
            is RegistrationData.Exception -> RegistrationDomain.Error(
                ErrorDomain.ApiError(model.exception, model.exception.message ?: "")
            )
        }
    }
}
