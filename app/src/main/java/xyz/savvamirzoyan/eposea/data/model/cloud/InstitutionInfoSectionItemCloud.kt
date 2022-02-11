package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
class InstitutionInfoSectionItemCloud(
    val id: String,
    val title: String
) : Model.Cloud
