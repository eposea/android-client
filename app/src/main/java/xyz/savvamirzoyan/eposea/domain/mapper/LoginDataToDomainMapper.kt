package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.data.LoginData
import xyz.savvamirzoyan.eposea.domain.model.LoginDomain

interface LoginDataToDomainMapper : Mapper<LoginData, LoginDomain> {

    fun map(model: LoginData): LoginDomain

    class Base : LoginDataToDomainMapper {
        override fun map(model: LoginData) = when (model) {
            is LoginData.Error -> {
                when (model.error) {
                    is ErrorData.ApiError -> LoginDomain.Error(R.string.error_api)
                    is ErrorData.NetworkError -> LoginDomain.Error(R.string.error_api)
                    is ErrorData.OtherError -> LoginDomain.Error(R.string.error_other)
                }
            }
            LoginData.NotAuthorized -> LoginDomain.NotAuthorized
            LoginData.ServerError -> LoginDomain.Error(R.string.error_api)
            LoginData.Success -> LoginDomain.Success
        }
    }
}