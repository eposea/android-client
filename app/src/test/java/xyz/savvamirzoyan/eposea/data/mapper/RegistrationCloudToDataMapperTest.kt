package xyz.savvamirzoyan.eposea.data.mapper

import retrofit2.Response
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData

class RegistrationCloudToDataMapperTest : RegistrationCloudToDataMapper {

    override fun map(model: Response<RegistrationCloud>): RegistrationData {
        TODO("Not yet implemented")
    }

    override fun map(exception: Exception): RegistrationData {
        TODO("Not yet implemented")
    }
}