package xyz.savvamirzoyan.eposea.data.mapper

import retrofit2.Response
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData

class RegistrationConfirmCloudToDataMapperTest : RegistrationConfirmCloudToDataMapper {

    override fun map(model: Response<RegistrationConfirmCloud>): RegistrationConfirmData {
        TODO("Not yet implemented")
    }

    override fun map(exception: Exception): RegistrationConfirmData {
        TODO("Not yet implemented")
    }
}
