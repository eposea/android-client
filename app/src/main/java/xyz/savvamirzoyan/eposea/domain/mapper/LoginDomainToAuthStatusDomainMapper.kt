package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.domain.model.LoginDomain

interface LoginDomainToAuthStatusDomainMapper : Mapper<LoginDomain, AuthStatusDomain> {

    fun map(model: LoginDomain): AuthStatusDomain

    class Base : LoginDomainToAuthStatusDomainMapper {
        override fun map(model: LoginDomain) = when (model) {
            is LoginDomain.Error -> AuthStatusDomain.Fail(R.string.error_api)
            LoginDomain.NotAuthorized -> AuthStatusDomain.Fail(R.string.not_authorized)
            LoginDomain.Success -> AuthStatusDomain.Success
        }
    }
}