package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class InstitutionsCloudResponse(
    val institutions: List<InstitutionCloud>
) : Model.Cloud
