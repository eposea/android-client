package xyz.savvamirzoyan.eposea.data.mapper

import retrofit2.Response
import xyz.savvamirzoyan.eposea.data.model.cloud.LoginCloud
import xyz.savvamirzoyan.eposea.data.model.data.LoginData

class LoginCloudToDataMapperTest : LoginCloudToDataMapper {

    override fun map(model: Response<LoginCloud>): LoginData {
        TODO()
    }

    override fun map(exception: Exception): LoginData {
        TODO()
    }
}