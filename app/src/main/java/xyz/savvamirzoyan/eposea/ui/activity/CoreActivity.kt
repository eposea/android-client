package xyz.savvamirzoyan.eposea.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CoreActivity : AppCompatActivity() {

    protected fun launchCoroutine(state: Lifecycle.State, function: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(state, function)
        }
    }
}