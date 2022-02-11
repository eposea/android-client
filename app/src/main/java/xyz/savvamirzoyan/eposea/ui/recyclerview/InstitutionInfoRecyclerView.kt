package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.ViewGroup
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.eposea.ui.core.CoreRecyclerViewAdapter
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoUi

private const val TYPE_LOADING = 0
private const val TYPE_ERROR = 1
private const val TYPE_DESCRIPTION_TITLE = 2
private const val TYPE_DESCRIPTION_BODY = 3

class InstitutionInfoRecyclerView(
    private val retry: Retry,
    diffUtilCallback: CoreDiffCallback<InstitutionInfoUi>
) : CoreRecyclerViewAdapter<InstitutionInfoUi, InstitutionInfoViewHolder>(diffUtilCallback) {

    override fun getItemViewType(position: Int) = when (items[position]) {
        is InstitutionInfoUi.Error -> TYPE_ERROR
        is InstitutionInfoUi.Text -> TYPE_DESCRIPTION_BODY
        is InstitutionInfoUi.Title -> TYPE_DESCRIPTION_TITLE
        InstitutionInfoUi.Loading -> TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionInfoViewHolder {
        return when (viewType) {
            TYPE_DESCRIPTION_TITLE ->
                InstitutionInfoViewHolder.Title(R.layout.view_holder_description_title.makeView(parent))
            TYPE_DESCRIPTION_BODY ->
                InstitutionInfoViewHolder.Text(
                    R.layout.view_holder_description_body_secondary.makeView(parent)
                )
            TYPE_LOADING ->
                InstitutionInfoViewHolder.Loading(R.layout.view_holder_loading.makeView(parent))
            else ->
                InstitutionInfoViewHolder.Error(retry, R.layout.view_holder_error.makeView(parent))
        }
    }
}