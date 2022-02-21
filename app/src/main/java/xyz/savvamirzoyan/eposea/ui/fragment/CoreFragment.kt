package xyz.savvamirzoyan.eposea.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoreViewModel

abstract class CoreFragment<VM : CoreViewModel> : Fragment() {

    protected val app: App by lazy { ((activity as CoreActivity).application as App) }

    protected lateinit var viewModel: VM

    protected fun launchCoroutine(function: suspend CoroutineScope.() -> Unit) {
        launchCoroutine(Lifecycle.State.STARTED, function)
    }

    @Suppress("SameParameterValue")
    protected fun launchCoroutine(lifecycleState: Lifecycle.State, function: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch { viewLifecycleOwner.repeatOnLifecycle(lifecycleState, function) }
    }
}