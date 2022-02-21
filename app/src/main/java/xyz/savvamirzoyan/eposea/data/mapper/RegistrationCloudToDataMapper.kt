package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData

interface RegistrationCloudToDataMapper : Mapper<RegistrationCloud, RegistrationData> {

    fun map(model: RegistrationCloud): RegistrationData
    fun map(exception: Exception): RegistrationData

    class Base : RegistrationCloudToDataMapper {

        override fun map(model: RegistrationCloud) = if (model.token != null && model.code in 200..299) {
            RegistrationData.Base(model.token)
        } else {
            RegistrationData.Error(model.code, model.message)
        }

        override fun map(exception: Exception) = RegistrationData.Exception(exception)
    }
}
