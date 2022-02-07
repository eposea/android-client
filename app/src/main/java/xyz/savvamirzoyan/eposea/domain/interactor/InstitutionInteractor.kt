package xyz.savvamirzoyan.eposea.domain.interactor

import xyz.savvamirzoyan.eposea.data.repository.InstitutionRepository
import xyz.savvamirzoyan.eposea.domain.mapper.InstitutionDataToDomainMapper
import xyz.savvamirzoyan.eposea.domain.model.InstitutionDomain

interface InstitutionInteractor {

    suspend fun fetchInstitutions(): List<InstitutionDomain>

    class Base(
        private val institutionsRepository: InstitutionRepository,
        private val institutionDataToDomainMapper: InstitutionDataToDomainMapper
    ) : InstitutionInteractor {

        override suspend fun fetchInstitutions(): List<InstitutionDomain> {
            val institutions = institutionsRepository.fetchInstitutions()
            return institutionDataToDomainMapper.map(institutions)
        }
    }
}