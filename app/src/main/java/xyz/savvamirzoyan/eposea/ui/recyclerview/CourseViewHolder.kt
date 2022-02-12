package xyz.savvamirzoyan.eposea.ui.recyclerview

import android.view.View
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.ViewHolderCourseBinding
import xyz.savvamirzoyan.eposea.databinding.ViewHolderErrorBinding
import xyz.savvamirzoyan.eposea.ui.core.CoreViewHolder
import xyz.savvamirzoyan.eposea.ui.model.CourseUi

sealed class CourseViewHolder(view: View) : CoreViewHolder<CourseUi>(view) {

    override fun bind(item: CourseUi) {
        when (item) {
            is CourseUi.Base -> bind(item)
            is CourseUi.Error -> bind(item)
            CourseUi.Loading -> {}
        }
    }

    open fun bind(item: CourseUi.Base) {}
    open fun bind(item: CourseUi.Error) {}

    class Base(view: View) : CourseViewHolder(view) {
        private val binding = ViewHolderCourseBinding.bind(view)

        override fun bind(item: CourseUi.Base) {
            binding.textViewCourseTitle.text = item.title
            binding.textViewCourseTeachers.text = item.teachers
        }
    }

    class Error(private val retry: Retry, view: View) : CourseViewHolder(view) {
        private val binding = ViewHolderErrorBinding.bind(view)

        override fun bind(item: CourseUi.Error) {
            binding.textViewErrorText.text = item.error
            binding.textViewErrorStackTrace.text = item.errorMessage
            binding.buttonRetry.setOnClickListener { retry.onRetry() }
        }
    }

    class Loading(view: View) : CourseViewHolder(view)
}