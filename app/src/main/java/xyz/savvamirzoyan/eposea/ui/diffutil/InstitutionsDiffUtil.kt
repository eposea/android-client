package xyz.savvamirzoyan.eposea.ui.diffutil

import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi

class InstitutionsDiffUtil : CoreDiffCallback<InstitutionUi>(
    { item1, item2 ->
        if (item1 is InstitutionUi.Base && item2 is InstitutionUi.Base) {
            item1.id == item2.id
        } else false
    }
)