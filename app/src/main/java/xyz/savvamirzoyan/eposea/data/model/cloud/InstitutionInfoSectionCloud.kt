package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
class InstitutionInfoSectionCloud(
    val title: String,
    val items: List<InstitutionInfoSectionItemCloud>
) : Model.Cloud