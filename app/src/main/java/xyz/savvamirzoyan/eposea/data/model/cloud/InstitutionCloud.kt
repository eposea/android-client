package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class InstitutionCloud(
    val id: String,
    val title: String,
    val imageUrl: String? = null
) : Model.Cloud
