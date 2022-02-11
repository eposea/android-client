package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class InstitutionInfoCloud(
    val title: String,
    val description: String,
    val sections: List<InstitutionInfoSectionCloud>,
    val imageUrl: String? = null
) : Model.Cloud
