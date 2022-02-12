package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.FragmentCoursesBinding
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.diffutil.CoursesDiffUtil
import xyz.savvamirzoyan.eposea.ui.recyclerview.CoursesRecyclerView
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoursesViewModel

class CoursesFragment : CoreFragment<CoursesViewModel>() {

    private lateinit var binding: FragmentCoursesBinding
    private lateinit var adapter: CoursesRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        viewModel = ((activity as CoreActivity).application as App).coursesViewModel

        adapter = CoursesRecyclerView(
            object : Retry {
                override fun onRetry() {
                }
            },
            CoursesDiffUtil()
        )

        launchCoroutine { coursesStatusListener() }

        binding.recyclerViewCourses.adapter = adapter
        binding.recyclerViewCourses.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private suspend fun coursesStatusListener() {
        viewModel.coursesFlow.collect { adapter.update(it) }
    }
}