package xyz.savvamirzoyan.eposea.data.repository

import xyz.savvamirzoyan.eposea.data.mapper.InstitutionInfoCloudToDataMapper
import xyz.savvamirzoyan.eposea.data.model.data.InstitutionInfoData
import xyz.savvamirzoyan.eposea.data.source.cloud.InstitutionInfoCloudSource

interface InstitutionInfoRepository {

    suspend fun fetchInstitutionInfo(institutionId: String): InstitutionInfoData

    class Base(
        private val institutionInfoCloudSource: InstitutionInfoCloudSource,
        private val institutionInfoCloudToDataMapper: InstitutionInfoCloudToDataMapper
    ) : InstitutionInfoRepository {
        override suspend fun fetchInstitutionInfo(institutionId: String): InstitutionInfoData = try {
            val institutionInfoCloud = institutionInfoCloudSource.fetchInstitutionInfo(institutionId)
            institutionInfoCloudToDataMapper.map(institutionInfoCloud)
        } catch (e: Exception) {
            institutionInfoCloudToDataMapper.map(e)
        }
    }
}