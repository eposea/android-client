package xyz.savvamirzoyan.eposea.ui.diffutil

import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.model.InstitutionWithCoursesUi

class InstitutionsWithCoursesDiffUtil : CoreDiffCallback<InstitutionWithCoursesUi>(
    { item1, item2 ->
        if (item1 is InstitutionWithCoursesUi.Institution && item2 is InstitutionWithCoursesUi.Institution) {
            item1.id == item2.id && item1.title == item2.title
        } else if (item1 is InstitutionWithCoursesUi.Course && item2 is InstitutionWithCoursesUi.Course) {
            item1.id == item2.id && item1.title == item2.title && item1.description == item2.description
        } else false
    },
    { item1, item2 ->
        if (item1 is InstitutionWithCoursesUi.Institution && item2 is InstitutionWithCoursesUi.Institution) {
            item1.id == item2.id
        } else if (item1 is InstitutionWithCoursesUi.Course && item2 is InstitutionWithCoursesUi.Course) {
            item1.id == item2.id
        } else false
    }
)