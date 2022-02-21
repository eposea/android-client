package xyz.savvamirzoyan.eposea.data.model.cloud

import xyz.savvamirzoyan.eposea.core.Model

data class RegistrationConfirmCloud(
    val code: Int,
    val message: String,
    val token: String? = null
) : Model.Cloud