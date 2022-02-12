package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import xyz.savvamirzoyan.eposea.core.Clicker
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.FragmentInstitutionsBinding
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.diffutil.InstitutionsDiffUtil
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi
import xyz.savvamirzoyan.eposea.ui.recyclerview.InstitutionsRecyclerView
import xyz.savvamirzoyan.eposea.ui.viewmodel.InstitutionsViewModel

class InstitutionsFragment : CoreFragment<InstitutionsViewModel>() {

    private lateinit var binding: FragmentInstitutionsBinding
    private lateinit var adapter: InstitutionsRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInstitutionsBinding.inflate(inflater, container, false)
        viewModel = ((activity as CoreActivity).application as App).institutionsViewModel

        adapter = InstitutionsRecyclerView(
            object : Retry {
                override fun onRetry() {
                    viewModel.onRetry()
                }
            },
            object : Clicker<InstitutionUi.Base> {
                override fun onClick(item: InstitutionUi.Base) {
                    viewModel.onClick(item)
                }
            },
            object : Clicker<InstitutionUi.BaseNoImage> {
                override fun onClick(item: InstitutionUi.BaseNoImage) {
                    viewModel.onClick(item)
                }
            },
            InstitutionsDiffUtil()
        )

        binding.recyclerViewInstitutions.adapter = adapter
        binding.recyclerViewInstitutions.layoutManager = LinearLayoutManager(context)
        binding.root.setOnRefreshListener { viewModel.onUpdate() }

        launchCoroutine { institutionsStateListener() }
        launchCoroutine { navigationStateListener() }

        return binding.root
    }

    private suspend fun institutionsStateListener() {
        viewModel.institutionsStateFlow.collect {
            adapter.update(it)
            binding.root.isRefreshing = false
        }
    }

    private suspend fun navigationStateListener() {
        viewModel.navigationStateFlow.collect { institutionId: String ->
            val action = InstitutionsFragmentDirections.toInstitutionInfoFragment(institutionId)
            findNavController().navigate(action)
        }
    }
}