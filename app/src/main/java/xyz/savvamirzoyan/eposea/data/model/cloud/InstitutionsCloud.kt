package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class InstitutionsCloud(
    val institutions: List<InstitutionCloud>
) : Model.Cloud
