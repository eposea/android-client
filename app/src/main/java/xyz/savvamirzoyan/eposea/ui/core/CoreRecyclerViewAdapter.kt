package xyz.savvamirzoyan.eposea.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class CoreRecyclerViewAdapter<T, VH : CoreViewHolder<T>>(
    private val diffCallback: CoreDiffCallback<T>
) : RecyclerView.Adapter<VH>() {

    protected val items = ArrayList<T>()

    open fun update(newItems: List<T>) {
        diffCallback.updateLists(items, newItems)
        val differentWords = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        differentWords.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    protected fun Int.makeView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}