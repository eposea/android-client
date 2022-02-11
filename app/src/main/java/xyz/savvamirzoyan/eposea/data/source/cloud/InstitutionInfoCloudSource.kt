package xyz.savvamirzoyan.eposea.data.source.cloud

import retrofit2.http.GET
import retrofit2.http.Path
import xyz.savvamirzoyan.eposea.data.model.cloud.InstitutionInfoCloud

interface InstitutionInfoCloudSource {

    @GET("institutions/{institutionId}")
    suspend fun fetchInstitutionInfo(@Path("institutionId") institutionId: String): InstitutionInfoCloud
}