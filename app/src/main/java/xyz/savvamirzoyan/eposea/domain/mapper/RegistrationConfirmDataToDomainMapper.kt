package xyz.savvamirzoyan.eposea.domain.mapper

import xyz.savvamirzoyan.eposea.core.ExceptionMapper
import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.data.model.data.RegistrationConfirmData
import xyz.savvamirzoyan.eposea.domain.model.RegistrationConfirmDomain

interface RegistrationConfirmDataToDomainMapper : Mapper<RegistrationConfirmData, RegistrationConfirmDomain> {

    fun map(model: RegistrationConfirmData): RegistrationConfirmDomain

    class Base(
        private val errorDataToDomainMapper: ExceptionMapper.BaseErrorDataToDomainMapper
    ) : RegistrationConfirmDataToDomainMapper {

        override fun map(model: RegistrationConfirmData) = when (model) {
            is RegistrationConfirmData.Base -> RegistrationConfirmDomain.Success
            is RegistrationConfirmData.Error -> RegistrationConfirmDomain.Error(errorDataToDomainMapper.mapError(model.error))
        }
    }
}