package xyz.savvamirzoyan.eposea.data.repository

import android.util.Patterns
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import xyz.savvamirzoyan.eposea.data.mapper.RegistrationCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.RegistrationConfirmCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.data.source.cloud.AuthService

interface AuthRepository {

    suspend fun sendRegisterCredentials(email: String, password: String): RegistrationData
    suspend fun sendConfirmationCode(code: String): RegistrationConfirmData
    suspend fun checkToken(): Boolean
    suspend fun login(email: String, password: String)

    fun isEmailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean

    @ObsoleteCoroutinesApi
    class Base(
        private val authService: AuthService,
        private val registrationCloudToDataMapper: RegistrationCloudToDataMapper,
        private val registrationConfirmCloudToDataMapper: RegistrationConfirmCloudToDataMapper
    ) : AuthRepository {

        private val variableContext = newSingleThreadContext("variable-context")
        private var tmpToken: String? = null
        private var token: String? = null

        override suspend fun sendRegisterCredentials(email: String, password: String): RegistrationData = try {
            val response: RegistrationCloud = authService.register(email, password)
            withContext(variableContext) { tmpToken = response.tmpToken }
            registrationCloudToDataMapper.map(response)
        } catch (e: Exception) {
            registrationCloudToDataMapper.map(e)
        }

        override suspend fun sendConfirmationCode(code: String): RegistrationConfirmData = try {
            registrationConfirmCloudToDataMapper.map(authService.registerProof(tmpToken!!, code))
        } catch (e: Exception) {
            registrationConfirmCloudToDataMapper.map(e)
        }

        override suspend fun checkToken(): Boolean = token != null

        override suspend fun login(email: String, password: String) = try {
            // TODO
            val response = authService.login(email, password)
        } catch (e: Exception) {
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