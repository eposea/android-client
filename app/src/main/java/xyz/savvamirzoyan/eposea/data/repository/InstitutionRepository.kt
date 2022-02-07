package xyz.savvamirzoyan.eposea.data.repository

import xyz.savvamirzoyan.eposea.data.mapper.InstitutionCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionData
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionCloudSource

interface InstitutionRepository {

    suspend fun fetchInstitutions(): List<InstitutionData>

    class Base(
        private val institutionCloudSource: InstitutionCloudSource,
        private val institutionCloudToDataMapper: InstitutionCloudToDataMapper
    ) : InstitutionRepository {

        override suspend fun fetchInstitutions(): List<InstitutionData> {
            return try {
                val institutionCloudResponse = institutionCloudSource.fetchInstitutions()
                institutionCloudToDataMapper.map(institutionCloudResponse.institutions)
            } catch (e: Exception) {
                listOf(institutionCloudToDataMapper.map(e))
            }
        }
    }
}