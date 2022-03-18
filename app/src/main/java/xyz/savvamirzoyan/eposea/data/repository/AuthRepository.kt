package xyz.savvamirzoyan.eposea.data.repository

import android.util.Patterns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import xyz.savvamirzoyan.eposea.core.CoreActor
import xyz.savvamirzoyan.eposea.data.mapper.LoginCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.RegistrationCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.RegistrationConfirmCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.data.LoginData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.data.source.cloud.AuthService

interface AuthRepository {

    suspend fun sendRegisterCredentials(email: String, password: String): RegistrationData
    suspend fun sendConfirmationCode(code: String): RegistrationConfirmData
    suspend fun checkToken(): Boolean
    suspend fun login(email: String, password: String): LoginData

    fun isEmailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean

    @ObsoleteCoroutinesApi
    class Base(
        private val authService: AuthService,
        private val loginCloudToDataMapper: LoginCloudToDataMapper,
        private val registrationCloudToDataMapper: RegistrationCloudToDataMapper,
        private val registrationConfirmCloudToDataMapper: RegistrationConfirmCloudToDataMapper,
        private val tokenDataStoreRepository: DataStoreRepository.Token,
        coroutineScope: CoroutineScope
    ) : AuthRepository {

        private var tmpToken = CoreActor<String>(coroutineScope)

        override suspend fun sendRegisterCredentials(email: String, password: String): RegistrationData = try {
            val response: RegistrationCloud = authService.register(email, password)
            tmpToken.set(response.tmpToken!!)
            registrationCloudToDataMapper.map(response)
        } catch (e: Exception) {
            registrationCloudToDataMapper.map(e)
        }

        override suspend fun sendConfirmationCode(code: String): RegistrationConfirmData = try {
            registrationConfirmCloudToDataMapper.map(authService.registerProof(tmpToken.get()!!, code))
        } catch (e: Exception) {
            registrationConfirmCloudToDataMapper.map(e)
        }

        override suspend fun checkToken() = tokenDataStoreRepository.fetchToken() != null

        override suspend fun login(email: String, password: String) = try {
            val response = authService.login(email, password)

            if (response.isSuccessful && response.body()?.token != null) {
                tokenDataStoreRepository.saveToken(response.body()!!.token)
            }

            loginCloudToDataMapper.map(response)
        } catch (e: Exception) {
            loginCloudToDataMapper.map(e)
        }

        override fun isEmailValid(email: String) =
            (Patterns.EMAIL_ADDRESS.matcher(email).matches())

        override fun isPasswordValid(password: String) =
            password.isNotBlank() && password.length >= 8 && password.hasNumbers()

        private fun String.hasNumbers(): Boolean {
            this.forEach { if (it.isDigit()) return true }
            return false
        }
    }
}