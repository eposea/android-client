package xyz.savvamirzoyan.eposea.data.source.cloud

import retrofit2.http.GET
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionsCloudResponse

interface InstitutionCloudSource {

    @GET("/institutions")
    suspend fun fetchInstitutions(): InstitutionsCloudResponse
}