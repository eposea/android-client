package xyz.savvamirzoyan.eposea.domain.model

import xyz.savvamirzoyan.eposea.core.Model

data class InstitutionInfoSectionDomain(
    val title: String,
    val items: List<InstitutionInfoSectionItemDomain>
) : Model.Domain