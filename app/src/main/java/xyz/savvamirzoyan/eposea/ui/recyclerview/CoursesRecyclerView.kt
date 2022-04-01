package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.ViewGroup
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.core.CoreRecyclerViewAdapter
import xyz.savvamirzoyan.eposea.ui.model.CourseUi

private const val TYPE_LOADING = 0
private const val TYPE_ERROR = 1
private const val TYPE_COURSE = 2

class CoursesRecyclerView(
    val retry: Retry,
    diffUtilCallback: CoreDiffCallback<CourseUi>
) : CoreRecyclerViewAdapter<CourseUi, CourseViewHolder>(diffUtilCallback) {

    override fun getItemViewType(position: Int) = when (items[position]) {
        is CourseUi.Base -> TYPE_COURSE
        is CourseUi.Error -> TYPE_ERROR
        CourseUi.Loading -> TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_COURSE -> CourseViewHolder.Base(R.layout.view_holder_course.makeView(parent))
        TYPE_LOADING -> CourseViewHolder.Loading(R.layout.view_holder_loading.makeView(parent))
        else -> CourseViewHolder.Error(retry, R.layout.view_holder_error.makeView(parent))
    }
}