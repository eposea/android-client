package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.View
import com.bumptech.glide.Glide
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.ViewHolderErrorBinding
import xyz.savvamirzoyan.eposea.databinding.ViewHolderInstitutionBinding
import xyz.savvamirzoyan.eposea.ui.core.CoreViewHolder
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi

sealed class InstitutionsViewHolder(view: View) : CoreViewHolder<InstitutionUi>(view) {

    override fun bind(item: InstitutionUi) {
        when (item) {
            is InstitutionUi.Error -> bind(item)
            is InstitutionUi.Base -> bind(item)
            else -> {}
        }
    }

    open fun bind(item: InstitutionUi.Error) {}
    open fun bind(item: InstitutionUi.Base) {}

    class Institution(view: View) : InstitutionsViewHolder(view) {
        private val binding = ViewHolderInstitutionBinding.bind(view)

        override fun bind(item: InstitutionUi.Base) {
            Glide.with(binding.root).load(item.imageUrl).into(binding.imageViewInstitutionLogo)
            binding.textViewInstitutionTitle.text = item.title
            binding.textViewTasksDone.text = item.tasksDone
            binding.textViewUrgentTasks.text = item.urgentTasks
        }
    }

    class Error(
        private val retry: Retry,
        view: View
    ) : InstitutionsViewHolder(view) {
        private val binding = ViewHolderErrorBinding.bind(view)

        override fun bind(item: InstitutionUi.Error) {
            binding.textViewErrorText.text = item.error
            binding.textViewErrorStackTrace.text = item.errorMessage
            binding.buttonRetry.setOnClickListener { retry.onRetry() }
        }
    }

    class Loading(view: View) : InstitutionsViewHolder(view)
}