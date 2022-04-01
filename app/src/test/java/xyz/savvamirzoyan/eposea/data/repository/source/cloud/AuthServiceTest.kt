package xyz.savvamirzoyan.eposea.data.repository.source.cloud

import retrofit2.Response
import xyz.savvamirzoyan.eposea.data.model.cloud.CheckTokenCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.LoginCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud
import xyz.savvamirzoyan.eposea.data.source.cloud.AuthService

class AuthServiceTest : AuthService {

    override suspend fun login(email: String, password: String): Response<LoginCloud> {
        TODO("Not yet implemented")
    }

    override suspend fun register(email: String, password: String): Response<RegistrationCloud> {
        TODO("Not yet implemented")
    }

    override suspend fun registerProof(tmpToken: String, code: String): Response<RegistrationConfirmCloud> {
        TODO("Not yet implemented")
    }

    override suspend fun checkToken(token: String): CheckTokenCloud {
        TODO("Not yet implemented")
    }
}