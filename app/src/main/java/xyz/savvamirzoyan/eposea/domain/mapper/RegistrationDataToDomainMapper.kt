package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationData
import xyz.savvamirzoyan.eposea.domain.model.RegistrationDomain

interface RegistrationDataToDomainMapper : Mapper<RegistrationData, RegistrationDomain> {

    fun map(model: RegistrationData): RegistrationDomain

    class Base(
        private val errorDataToDomainMapper: ExceptionMapper.BaseErrorDataToDomainMapper
    ) : RegistrationDataToDomainMapper {

        override fun map(model: RegistrationData) = when (model) {
            is RegistrationData.Base -> RegistrationDomain.Base
            is RegistrationData.Error -> RegistrationDomain.Error(errorDataToDomainMapper.mapError(model.error))
        }
    }
}
