package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.FragmentInstitutionInfoBinding
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.diffutil.InstitutionInfoDiffUtil
import xyz.savvamirzoyan.eposea.ui.recyclerview.InstitutionInfoRecyclerView
import xyz.savvamirzoyan.eposea.ui.viewmodel.InstitutionInfoViewModel


class InstitutionInfoFragment : CoreFragment<InstitutionInfoViewModel>() {

    private val args by navArgs<InstitutionInfoFragmentArgs>()
    private lateinit var binding: FragmentInstitutionInfoBinding
    private lateinit var adapter: InstitutionInfoRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInstitutionInfoBinding.inflate(inflater, container, false)
        viewModel = ((activity as CoreActivity).application as App).institutionInfoViewModel

        adapter = InstitutionInfoRecyclerView(
            object : Retry {
                override fun onRetry() {
                    viewModel.fetchInstitutionInfo(args.institutionId)
                }
            },
            InstitutionInfoDiffUtil()
        )

        binding.recyclerViewInstitutionInfo.adapter = adapter
        binding.recyclerViewInstitutionInfo.layoutManager = LinearLayoutManager(context)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.fetchInstitutionInfo(args.institutionId) }

        launchCoroutine { institutionToolbarInfoStateListener() }
        launchCoroutine { institutionInfoStateListener() }

        viewModel.fetchInstitutionInfo(args.institutionId)

        return binding.root
    }

    private suspend fun institutionToolbarInfoStateListener() {
        viewModel.institutionToolbarInfoFlow.collect {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.imageViewInstitutionLogo)
            binding.collapsingToolbar.title = it.title
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private suspend fun institutionInfoStateListener() {
        viewModel.institutionInfoFlow.collect { adapter.update(it) }
    }
}