package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class InstitutionCloud(
    val id: String,
    @SerialName("name") val title: String,
    val imageUrl: String? = null
) : Model.Cloud
