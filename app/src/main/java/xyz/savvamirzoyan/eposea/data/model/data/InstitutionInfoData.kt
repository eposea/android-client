package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class InstitutionInfoData : Model.Data {

    data class Base(
        val toolbarInfo: InstitutionInfoToolbarData,
        val description: String,
        val sections: List<InstitutionInfoSectionData>
    ) : InstitutionInfoData()

    data class Error(val error: ErrorData) : InstitutionInfoData()
}
