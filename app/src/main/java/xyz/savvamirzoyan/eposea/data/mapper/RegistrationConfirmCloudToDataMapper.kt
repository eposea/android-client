package xyz.savvamirzoyan.eposea.data.mapper

import retrofit2.Response
import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData

interface RegistrationConfirmCloudToDataMapper : Mapper<RegistrationConfirmCloud, RegistrationConfirmData> {

    fun map(model: Response<RegistrationConfirmCloud>): RegistrationConfirmData
    fun map(exception: Exception): RegistrationConfirmData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : RegistrationConfirmCloudToDataMapper {

        override fun map(model: Response<RegistrationConfirmCloud>) = when (model.code()) {
            in 200..299 -> RegistrationConfirmData.Success(model.body()!!.token!!)
            401 -> RegistrationConfirmData.NotAuthorized
            else -> RegistrationConfirmData.ServerError
        }

        override fun map(exception: Exception) =
            RegistrationConfirmData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}