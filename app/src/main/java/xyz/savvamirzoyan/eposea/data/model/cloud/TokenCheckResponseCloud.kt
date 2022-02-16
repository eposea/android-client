package xyz.savvamirzoyan.eposea.data.model.cloud

import xyz.savvamirzoyan.eposea.core.Model

data class TokenCheckResponseCloud(
    val result: Boolean,
    val code: Int
) : Model.Cloud