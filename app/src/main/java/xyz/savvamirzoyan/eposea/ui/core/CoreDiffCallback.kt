package xyz.savvamirzoyan.eposea.ui.core

import androidx.recyclerview.widget.DiffUtil
import xyz.savvamirzoyan.eposea.core.Model

abstract class CoreDiffCallback<T : Model.Ui>(
    private val _areContentsTheSame: (T, T) -> Boolean,
    private val _areItemsTheSame: (T, T) -> Boolean,
) : DiffUtil.Callback() {

    private var oldList: List<T> = listOf()
    private var newList: List<T> = listOf()

    fun updateLists(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return _areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return _areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }
}