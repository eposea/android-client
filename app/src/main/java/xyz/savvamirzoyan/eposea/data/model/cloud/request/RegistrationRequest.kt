package xyz.savvamirzoyan.eposea.data.model.cloud.request

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class RegistrationRequest(
    val email: String,
    val password: String
) : Model.Cloud
