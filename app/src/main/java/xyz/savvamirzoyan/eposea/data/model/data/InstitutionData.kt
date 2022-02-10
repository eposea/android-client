package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model
import xyz.savvamirzoyan.eposea.data.error.ErrorData

sealed class InstitutionData : Model.Data {

    data class Base(
        val id: String,
        val title: String,
        val imageUrl: String?
    ) : InstitutionData()

    data class Error(val error: ErrorData) : InstitutionData()
}
