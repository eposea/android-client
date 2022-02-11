package xyz.savvamirzoyan.eposea.domain.interactor

import xyz.savvamirzoyan.eposea.data.repository.InstitutionInfoRepository
import xyz.savvamirzoyan.eposea.domain.mapper.InstitutionInfoDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.model.InstitutionInfoDomain

interface InstitutionInfoInteractor {

    suspend fun fetchInstitutionInfo(institutionId: String): InstitutionInfoDomain

    class Base(
        private val institutionInfoRepository: InstitutionInfoRepository,
        private val institutionInfoDataToDomainMapper: InstitutionInfoDataToDomainMapper
    ) : InstitutionInfoInteractor {

        override suspend fun fetchInstitutionInfo(institutionId: String): InstitutionInfoDomain {
            val institutionInfoData = institutionInfoRepository.fetchInstitutionInfo(institutionId)
            return institutionInfoDataToDomainMapper.map(institutionInfoData)
        }
    }
}