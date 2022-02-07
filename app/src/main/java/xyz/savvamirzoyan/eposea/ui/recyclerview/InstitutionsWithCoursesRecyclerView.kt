package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.ViewGroup
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.core.CoreRecyclerViewAdapter
import xyz.savvamirzoyan.eposea.ui.model.InstitutionWithCoursesUi

private const val TYPE_INSTITUTION = 0
private const val TYPE_COURSE = 1
private const val TYPE_LOADING = 2
private const val TYPE_ERROR = 3

class InstitutionsWithCoursesRecyclerView(
    private val retry: Retry,
    diffUtilCallback: CoreDiffCallback<InstitutionWithCoursesUi>
) : CoreRecyclerViewAdapter<InstitutionWithCoursesUi, InstitutionsWithCoursesViewHolder>(diffUtilCallback) {

    override fun getItemViewType(position: Int) = when (items[position]) {
        is InstitutionWithCoursesUi.Course -> TYPE_COURSE
        is InstitutionWithCoursesUi.Error -> TYPE_ERROR
        is InstitutionWithCoursesUi.Institution -> TYPE_INSTITUTION
        InstitutionWithCoursesUi.Loading -> TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionsWithCoursesViewHolder =
        when (viewType) {
            TYPE_INSTITUTION ->
                InstitutionsWithCoursesViewHolder.Institution(R.layout.view_holder_institution.makeView(parent))
            TYPE_COURSE -> InstitutionsWithCoursesViewHolder.Course(R.layout.view_holder_course.makeView(parent))
            TYPE_LOADING -> InstitutionsWithCoursesViewHolder.Loading(R.layout.view_holder_loading.makeView(parent))
            else -> InstitutionsWithCoursesViewHolder.Error(retry, R.layout.view_holder_error.makeView(parent))
        }
}
