package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.error.ErrorData
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData

interface RegistrationConfirmCloudToDataMapper : Mapper<RegistrationConfirmCloud, RegistrationConfirmData> {

    fun map(model: RegistrationConfirmCloud): RegistrationConfirmData
    fun map(exception: Exception): RegistrationConfirmData

    class Base : RegistrationConfirmCloudToDataMapper {

        override fun map(model: RegistrationConfirmCloud) = RegistrationConfirmData.Base(model.token!!)
        override fun map(exception: Exception) = RegistrationConfirmData.Error(
            ErrorData.NetworkError(exception, exception.message ?: "")
        )
    }
}