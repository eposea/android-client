package xyz.savvamirzoyan.eposea.data.model.cloud

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class LoginCloud(
    val status: Boolean,
    val token: String
) : Model.Cloud
