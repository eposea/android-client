package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.util.Log
import android.view.View
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.ViewHolderCourseBinding
import xyz.savvamirzoyan.eposea.databinding.ViewHolderErrorBinding
import xyz.savvamirzoyan.eposea.databinding.ViewHolderInstitutionBinding
import xyz.savvamirzoyan.eposea.ui.core.CoreViewHolder
import xyz.savvamirzoyan.eposea.ui.model.InstitutionWithCoursesUi

sealed class InstitutionsWithCoursesViewHolder(view: View) : CoreViewHolder<InstitutionWithCoursesUi>(view) {

    override fun bind(item: InstitutionWithCoursesUi) {
        when (item) {
            is InstitutionWithCoursesUi.Course -> bind(item)
            is InstitutionWithCoursesUi.Error -> bind(item)
            is InstitutionWithCoursesUi.Institution -> bind(item)
            else -> {
                Log.d("SPAMEGGS", "NO bind")
            }
        }
    }

    open fun bind(item: InstitutionWithCoursesUi.Course) {}
    open fun bind(item: InstitutionWithCoursesUi.Error) {}
    open fun bind(item: InstitutionWithCoursesUi.Institution) {}

    class Institution(view: View) : InstitutionsWithCoursesViewHolder(view) {
        private val binding = ViewHolderInstitutionBinding.bind(view)

        override fun bind(item: InstitutionWithCoursesUi.Institution) {
            Log.d("SPAMEGGS", "Institution bind")
            binding.textViewInstitutionTitle.text = item.title
        }
    }

    class Course(view: View) : InstitutionsWithCoursesViewHolder(view) {
        private val binding = ViewHolderCourseBinding.bind(view)

        override fun bind(item: InstitutionWithCoursesUi.Course) {
            Log.d("SPAMEGGS", "Course bind")
            binding.textViewCourseTitle.text = item.title
            binding.textViewCourseDescription.text = item.description
        }
    }

    class Error(
        private val retry: Retry,
        view: View
    ) : InstitutionsWithCoursesViewHolder(view) {
        private val binding = ViewHolderErrorBinding.bind(view)

        override fun bind(item: InstitutionWithCoursesUi.Error) {
            binding.textViewErrorText.text = item.error
            binding.buttonRetry.setOnClickListener { retry.onRetry() }
        }
    }

    class Loading(view: View) : InstitutionsWithCoursesViewHolder(view)
}