package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model

data class CourseDomain(
    val id: String,
    val title: String,
    val description: String
) : Model.Domain
