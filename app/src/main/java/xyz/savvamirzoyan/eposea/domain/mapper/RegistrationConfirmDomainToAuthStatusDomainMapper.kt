package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.error.ErrorDomain
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.RegistrationConfirmDomain

interface RegistrationConfirmDomainToAuthStatusDomainMapper : Mapper<RegistrationConfirmDomain, AuthStatusDomain> {

    fun map(model: RegistrationConfirmDomain): AuthStatusDomain

    class Base : RegistrationConfirmDomainToAuthStatusDomainMapper {
        override fun map(model: RegistrationConfirmDomain) = when (model) {
            RegistrationConfirmDomain.Success -> AuthStatusDomain.Success
            RegistrationConfirmDomain.NotAuthorized -> AuthStatusDomain.Fail(R.string.invalid_code_from_email)
            RegistrationConfirmDomain.ServerError -> AuthStatusDomain.Fail(R.string.error_api)
            is RegistrationConfirmDomain.Error -> {
                when (model.error) {
                    is ErrorDomain.ApiError -> AuthStatusDomain.Fail(R.string.error_api)
                    is ErrorDomain.OtherError -> AuthStatusDomain.Fail(R.string.error_other)
                }
            }
        }
    }
}