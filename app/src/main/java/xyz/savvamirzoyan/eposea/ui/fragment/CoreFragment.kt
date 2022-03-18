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

@Suppress("UNCHECKED_CAST")
abstract class CoreFragment<ACT : CoreActivity, VM : CoreViewModel> : Fragment() {

    protected val app: App by lazy { ((activity as ACT).application as App) }
    protected val act: ACT by lazy { (activity as ACT) }

    protected lateinit var viewModel: VM

    protected fun launchCoroutine(function: suspend CoroutineScope.() -> Unit) {
        launchCoroutine(Lifecycle.State.STARTED, function)
    }

    @Suppress("SameParameterValue")
    protected fun launchCoroutine(lifecycleState: Lifecycle.State, function: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch { viewLifecycleOwner.repeatOnLifecycle(lifecycleState, function) }
    }
}