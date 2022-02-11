package xyz.savvamirzoyan.eposea.ui.fragment

import androidx.fragment.app.Fragment
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoreViewModel

abstract class CoreFragment<VM : CoreViewModel> : Fragment() {

    protected lateinit var viewModel: VM
}