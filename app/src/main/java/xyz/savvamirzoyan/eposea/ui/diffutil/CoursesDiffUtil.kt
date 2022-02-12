package xyz.savvamirzoyan.eposea.ui.diffutil

import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.model.CourseUi

class CoursesDiffUtil : CoreDiffCallback<CourseUi>(
    { item1, item2 ->
        if (item1 is CourseUi.Base && item2 is CourseUi.Base) {
            item1.id == item2.id
        } else false
    }
)