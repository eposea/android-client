package xyz.savvamirzoyan.eposea.ui.mapper

import xyz.savvamirzoyan.eposea.core.Mapper
import xyz.savvamirzoyan.eposea.domain.model.EditTextStatusDomain
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.model.EditTextStatusUi

interface EditTextStatusDomainToUiMapper : Mapper<EditTextStatusDomain, EditTextStatusUi> {

    fun map(model: EditTextStatusDomain): EditTextStatusUi

    class Base(
        private val resourceManager: ResourceManager,
    ) : EditTextStatusDomainToUiMapper {

        override fun map(model: EditTextStatusDomain) = EditTextStatusUi(
            model.helperText?.let { resourceManager.getString(it) },
            model.error?.let { resourceManager.getString(it) }
        )
    }
}
