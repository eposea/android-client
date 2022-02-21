package xyz.savvamirzoyan.eposea.data.model.cloud.request

import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.eposea.core.Model

@Serializable
data class RegistrationConfirmRequest(
    val tmpToken: String,
    val code: String
) : Model.Cloud
