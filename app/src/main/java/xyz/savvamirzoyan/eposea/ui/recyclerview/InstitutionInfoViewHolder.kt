package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.View
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.ViewHolderDescriptionBodyBinding
import xyz.savvamirzoyan.eposea.databinding.ViewHolderDescriptionTitleBinding
import xyz.savvamirzoyan.eposea.databinding.ViewHolderErrorBinding
import xyz.savvamirzoyan.eposea.ui.core.CoreViewHolder
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoUi


sealed class InstitutionInfoViewHolder(view: View) : CoreViewHolder<InstitutionInfoUi>(view) {

    override fun bind(item: InstitutionInfoUi) {
        when (item) {
            is InstitutionInfoUi.Error -> bind(item)
            is InstitutionInfoUi.Title -> bind(item)
            is InstitutionInfoUi.Text -> bind(item)
            InstitutionInfoUi.Loading -> {}
        }
    }

    open fun bind(item: InstitutionInfoUi.Error) {}
    open fun bind(item: InstitutionInfoUi.Title) {}
    open fun bind(item: InstitutionInfoUi.Text) {}

    class Title(view: View) : InstitutionInfoViewHolder(view) {
        private val binding = ViewHolderDescriptionTitleBinding.bind(view)

        override fun bind(item: InstitutionInfoUi.Title) {
            binding.root.text = item.text
        }
    }

    class Text(view: View) : InstitutionInfoViewHolder(view) {
        private val binding = ViewHolderDescriptionBodyBinding.bind(view)

        override fun bind(item: InstitutionInfoUi.Text) {
            binding.root.text = item.text
        }
    }

    class Error(private val retry: Retry, view: View) : InstitutionInfoViewHolder(view) {
        private val binding = ViewHolderErrorBinding.bind(view)

        override fun bind(item: InstitutionInfoUi.Error) {
            binding.textViewErrorText.text = item.error
            binding.textViewErrorStackTrace.text = item.errorMessage
            binding.buttonRetry.setOnClickListener { retry.onRetry() }
        }
    }

    class Loading(view: View) : InstitutionInfoViewHolder(view)
}