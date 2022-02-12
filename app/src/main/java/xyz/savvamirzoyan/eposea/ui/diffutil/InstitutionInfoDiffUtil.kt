package xyz.savvamirzoyan.eposea.ui.diffutil

import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoUi

class InstitutionInfoDiffUtil : CoreDiffCallback<InstitutionInfoUi>(
    { item1, item2 -> item1 == item2 }
)