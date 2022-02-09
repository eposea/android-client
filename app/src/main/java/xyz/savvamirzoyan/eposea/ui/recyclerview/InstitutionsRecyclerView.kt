package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.ViewGroup
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.core.CoreRecyclerViewAdapter
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi

private const val TYPE_INSTITUTION = 0
private const val TYPE_LOADING = 1
private const val TYPE_ERROR = 2

class InstitutionsWithCoursesRecyclerView(
    private val retry: Retry,
    diffUtilCallback: CoreDiffCallback<InstitutionUi>
) : CoreRecyclerViewAdapter<InstitutionUi, InstitutionsViewHolder>(diffUtilCallback) {

    override fun getItemViewType(position: Int) = when (items[position]) {
        is InstitutionUi.Error -> TYPE_ERROR
        is InstitutionUi.Base -> TYPE_INSTITUTION
        InstitutionUi.Loading -> TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionsViewHolder =
        when (viewType) {
            TYPE_INSTITUTION ->
                InstitutionsViewHolder.Institution(R.layout.view_holder_institution.makeView(parent))
            TYPE_LOADING -> InstitutionsViewHolder.Loading(R.layout.view_holder_loading.makeView(parent))
            else -> InstitutionsViewHolder.Error(retry, R.layout.view_holder_error.makeView(parent))
        }
}
