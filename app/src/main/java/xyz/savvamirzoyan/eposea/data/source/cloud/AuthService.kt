package xyz.savvamirzoyan.eposea.data.source.cloud

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.savvamirzoyan.eposea.data.model.cloud.CheckTokenCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.LoginCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationCloud
import xyz.savvamirzoyan.eposea.data.model.cloud.RegistrationConfirmCloud

interface AuthService {

    @POST("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): LoginCloud

    @POST("register")
    suspend fun register(
        @Query("email") email: String,
        @Query("password") password: String
    ): RegistrationCloud

    @POST("register/proof")
    suspend fun registerProof(
        @Query("tmp_token") tmpToken: String,
        @Query("code") code: String
    ): RegistrationConfirmCloud

    @GET("checkToken")
    suspend fun checkToken(
        @Query("token") token: String
    ): CheckTokenCloud

//    @POST("registration")
//    suspend fun register(@Body registrationRequest: RegistrationRequest): RegistrationCloud
//
//    @POST("registration/confirm")
//    suspend fun confirmRegistration(@Body registrationConfirmRequest: RegistrationConfirmRequest): RegistrationConfirmCloud
}