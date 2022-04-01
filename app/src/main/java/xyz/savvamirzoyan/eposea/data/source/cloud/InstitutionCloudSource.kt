package xyz.savvamirzoyan.eposea.data.source.cloud

import retrofit2.http.GET
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionsCloud

interface InstitutionCloudSource {

    @GET("aggregator/institutions")
    suspend fun fetchInstitutions(): InstitutionsCloud
}