package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.FragmentInstitutionsBinding
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.diffutil.InstitutionsWithCoursesDiffUtil
import xyz.savvamirzoyan.eposea.ui.recyclerview.InstitutionsWithCoursesRecyclerView
import xyz.savvamirzoyan.eposea.ui.viewmodel.InstitutionViewModel

@ExperimentalSerializationApi
class InstitutionsFragment : CoreFragment<InstitutionViewModel>() {

    private lateinit var binding: FragmentInstitutionsBinding
    private lateinit var adapter: InstitutionsWithCoursesRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInstitutionsBinding.inflate(inflater, container, false)
        viewModel = ((activity as CoreActivity).application as App).institutionViewModel

        adapter = InstitutionsWithCoursesRecyclerView(
            object : Retry {
                override fun onRetry() {
                    viewModel.onRetry()
                }
            },
            InstitutionsWithCoursesDiffUtil()
        )

        binding.recyclerViewInstitutions.adapter = adapter
        binding.recyclerViewInstitutions.layoutManager = LinearLayoutManager(context)

        binding.root.setOnRefreshListener {
            viewModel.onUpdate()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                institutionsWithCoursesStateListener()
            }
        }

        return binding.root
    }

    private suspend fun institutionsWithCoursesStateListener() {
        viewModel.institutionsStateFlow.collect {
            adapter.update(it)
            binding.root.isRefreshing = false
        }
    }
}