package xyz.savvamirzoyan.eposea.data.model.data

import xyz.savvamirzoyan.eposea.core.Model

data class InstitutionInfoSectionData(
    val title: String,
    val items: List<InstitutionInfoSectionItemData>
) : Model.Data
