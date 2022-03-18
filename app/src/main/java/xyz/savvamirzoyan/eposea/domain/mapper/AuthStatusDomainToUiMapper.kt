package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.model.AuthStatusDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.AuthStatusUi

interface AuthStatusDomainToUiMapper : Mapper<AuthStatusDomain, AuthStatusUi> {

    fun map(model: AuthStatusDomain): AuthStatusUi

    class Base(
        private val resourceManager: ResourceManager
    ) : AuthStatusDomainToUiMapper {
        override fun map(model: AuthStatusDomain) = when (model) {
            is AuthStatusDomain.Fail -> AuthStatusUi.Fail(resourceManager.getString(model.message))
            AuthStatusDomain.Success -> AuthStatusUi.Success
        }
    }
}