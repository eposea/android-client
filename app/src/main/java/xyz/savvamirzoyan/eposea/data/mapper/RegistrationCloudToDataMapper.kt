package xyz.savvamirzoyan.eposea.data.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData

interface RegistrationCloudToDataMapper : Mapper<RegistrationCloud, RegistrationData> {

    fun map(model: RegistrationCloud): RegistrationData
    fun map(exception: Exception): RegistrationData

    class Base(
        private val exceptionToErrorDataMapper: ExceptionMapper.ExceptionToErrorDataMapper
    ) : RegistrationCloudToDataMapper {

        override fun map(model: RegistrationCloud) = RegistrationData.Base(model.tmpToken!!)
        override fun map(exception: Exception) =
            RegistrationData.Error(exceptionToErrorDataMapper.mapException(exception))
    }
}
