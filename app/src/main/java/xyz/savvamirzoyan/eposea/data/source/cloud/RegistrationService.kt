package xyz.savvamirzoyan.eposea.data.source.cloud

import retrofit2.http.Body
import retrofit2.http.POST
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.request.RegistrationConfirmRequest
import xyz.savvamirzoyan.eposea.data.model.cloud.request.RegistrationRequest

interface RegistrationService {

    @POST("registration")
    suspend fun register(@Body registrationRequest: RegistrationRequest): RegistrationCloud

    @POST("registration/confirm")
    suspend fun confirmRegistration(@Body registrationConfirmRequest: RegistrationConfirmRequest): RegistrationConfirmCloud
}