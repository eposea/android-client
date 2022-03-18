package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.FragmentCoursesBinding
import xyz.savvamirzoyan.eposea.ui.activity.MainActivity
import xyz.savvamirzoyan.eposea.ui.diffutil.CoursesDiffUtil
import xyz.savvamirzoyan.eposea.ui.recyclerview.CoursesRecyclerView
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoursesViewModel

@ExperimentalSerializationApi
class CoursesFragment : CoreFragment<MainActivity, CoursesViewModel>() {

    private lateinit var binding: FragmentCoursesBinding
    private lateinit var adapter: CoursesRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        viewModel = app.coursesViewModel

        adapter = CoursesRecyclerView(
            object : Retry {
                override fun onRetry() {
                    viewModel.fetchInfo()
                }
            },
            CoursesDiffUtil()
        )

        launchCoroutine { coursesStatusListener() }
        launchCoroutine { toolbarTitleStatusListener() }

        binding.recyclerViewCourses.adapter = adapter
        binding.recyclerViewCourses.layoutManager = LinearLayoutManager(context)
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.fetchInfo() }

        return binding.root
    }

    private suspend fun coursesStatusListener() {
        viewModel.coursesFlow.collect {
            adapter.update(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private suspend fun toolbarTitleStatusListener() {
        viewModel.institutionTitleFlow.collect {
            binding.toolbar.title = it
        }
    }
}