package xyz.savvamirzoyan.eposea.data.repository

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import xyz.savvamirzoyan.eposea.data.mapper.RegistrationCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.mapper.RegistrationConfirmCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.model.cloud.request.RegistrationConfirmRequest
import xyz.savvamirzoyan.eposea.data.model.cloud.request.RegistrationRequest
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.data.source.cloud.RegistrationService

interface AuthRepository {

    suspend fun sendCredentials(email: String, password: String): RegistrationData
    suspend fun sendConfirmationCode(code: String): RegistrationConfirmData

    @ObsoleteCoroutinesApi
    class Base(
        private val registrationService: RegistrationService,
        private val registrationCloudToDataMapper: RegistrationCloudToDataMapper,
        private val registrationConfirmCloudToDataMapper: RegistrationConfirmCloudToDataMapper
    ) : AuthRepository {

        private val variableContext = newSingleThreadContext("variable-context")
        private var tmpToken: String? = null

        override suspend fun sendCredentials(email: String, password: String): RegistrationData = try {
            val request = RegistrationRequest(email, password)
            val response = registrationService.register(request)
            withContext(variableContext) { tmpToken = response.token }
            registrationCloudToDataMapper.map(response)
        } catch (e: Exception) {
            registrationCloudToDataMapper.map(e)
        }

        override suspend fun sendConfirmationCode(code: String): RegistrationConfirmData = try {
            val token: String
            withContext(variableContext) { token = tmpToken!! }
            val request = RegistrationConfirmRequest(token, code)
            registrationConfirmCloudToDataMapper.map(registrationService.confirmRegistration(request))
        } catch (e: Exception) {
            registrationConfirmCloudToDataMapper.map(e)
        }
    }
}