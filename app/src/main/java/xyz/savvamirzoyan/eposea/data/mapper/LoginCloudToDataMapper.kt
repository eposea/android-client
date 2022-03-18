package xyz.savvamirzoyan.eposea.data.mapper

import retrofit2.Response
import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.LoginCloud
import xyz.savvamirzoyan.eposea.data.model.data.LoginData

interface LoginCloudToDataMapper : Mapper<LoginCloud, LoginData> {

    fun map(model: Response<LoginCloud>): LoginData
    fun map(exception: Exception): LoginData

    class Base(
        private val exceptionMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : LoginCloudToDataMapper {
        override fun map(model: Response<LoginCloud>): LoginData = when {
            model.code() in 200..299 -> LoginData.Success
            model.code() == 401 -> LoginData.NotAuthorized
            else -> LoginData.ServerError
        }

        override fun map(exception: Exception): LoginData =
            LoginData.Error(exceptionMapper.mapException(exception))
    }
}