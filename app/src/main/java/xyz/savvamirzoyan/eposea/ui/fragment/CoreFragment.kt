package xyz.savvamirzoyan.eposea.ui.fragment

import androidx.fragment.app.Fragment
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoreViewModel

@ExperimentalSerializationApi
abstract class CoreFragment<VM : CoreViewModel> : Fragment() {

    protected lateinit var viewModel: VM
}