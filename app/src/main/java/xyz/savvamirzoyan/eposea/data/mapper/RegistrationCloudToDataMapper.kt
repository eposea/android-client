package xyz.savvamirzoyan.eposea.data.mapper

import retrofit2.Response
import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData

interface RegistrationCloudToDataMapper : Mapper<RegistrationCloud, RegistrationData> {

    fun map(model: Response<RegistrationCloud>): RegistrationData
    fun map(exception: Exception): RegistrationData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : RegistrationCloudToDataMapper {

        override fun map(model: Response<RegistrationCloud>) = when (model.code()) {
            in 200..299 -> RegistrationData.Success(model.body()!!.tmpToken!!)
            401 -> RegistrationData.NotAuthorized
            else -> RegistrationData.ServerError
        }

        override fun map(exception: Exception) =
            RegistrationData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}
