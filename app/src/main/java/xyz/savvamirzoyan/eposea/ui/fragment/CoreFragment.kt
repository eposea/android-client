package xyz.savvamirzoyan.eposea.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.ui.activity.CoreActivity
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoreViewModel

@ExperimentalSerializationApi
abstract class CoreFragment<VM : CoreViewModel> : Fragment() {

    protected lateinit var viewModel: VM
    protected abstract fun viewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (requireActivity() as CoreActivity).viewModel(viewModelClass(), this)
    }
}