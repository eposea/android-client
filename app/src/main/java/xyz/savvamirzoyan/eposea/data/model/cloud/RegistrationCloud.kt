package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class RegistrationCloud(
    @SerialName("tmp_token") val tmpToken: String? = null
) : Model.Cloud