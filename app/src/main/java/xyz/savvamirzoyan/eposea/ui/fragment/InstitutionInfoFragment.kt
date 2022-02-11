package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.core.Retry
import xyz.savvamirzoyan.eposea.databinding.FragmentInstitutionInfoBinding
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.diffutil.InstitutionInfoUiDiffUtil
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
                    viewModel.onRetry()
                }
            },
            InstitutionInfoUiDiffUtil()
        )

        binding.recyclerViewInstitutionInfo.adapter = adapter
        binding.recyclerViewInstitutionInfo.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                institutionToolbarInfoStateListener()
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                institutionInfoStateListener()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.fetchInstitutionInfo(args.institutionId)

        return binding.root
    }

    private suspend fun institutionToolbarInfoStateListener() {
        viewModel.institutionToolbarInfoFlow.collect {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.imageViewInstitutionLogo)
            binding.collapsingToolbar.title = it.title
        }
    }

    private suspend fun institutionInfoStateListener() {
        viewModel.institutionInfoFlow.collect { adapter.update(it) }
    }
}