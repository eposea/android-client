package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.domain.model.RegistrationDomain

interface RegistrationDataToDomainMapper : Mapper<RegistrationData, RegistrationDomain> {

    fun map(model: RegistrationData): RegistrationDomain

    class Base : RegistrationDataToDomainMapper {

        override fun map(model: RegistrationData) = when (model) {
            is RegistrationData.Success -> RegistrationDomain.Base
            is RegistrationData.Error -> {
                when (model.error) {
                    is ErrorData.ApiError -> RegistrationDomain.Error(R.string.error_api)
                    is ErrorData.NetworkError -> RegistrationDomain.Error(R.string.error_api)
                    is ErrorData.OtherError -> RegistrationDomain.Error(R.string.error_other)
                }
            }
            RegistrationData.NotAuthorized -> RegistrationDomain.Error(R.string.not_authorized)
            RegistrationData.ServerError -> RegistrationDomain.Base
        }
    }
}