package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData

interface RegistrationConfirmCloudToDataMapper : Mapper<RegistrationConfirmCloud, RegistrationConfirmData> {

    fun map(model: RegistrationConfirmCloud): RegistrationConfirmData
    fun map(exception: Exception): RegistrationConfirmData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : RegistrationConfirmCloudToDataMapper {

        override fun map(model: RegistrationConfirmCloud) = RegistrationConfirmData.Base(model.token!!)
        override fun map(exception: Exception) =
            RegistrationConfirmData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}