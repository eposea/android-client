package xyz.savvamirzoyan.eposea.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.eposea.ui.App
import xyz.savvamirzoyan.eposea.ui.viewmodel.CoreViewModel

@ExperimentalSerializationApi
abstract class CoreActivity : AppCompatActivity() {

    fun <VM : CoreViewModel> viewModel(model: Class<VM>, owner: ViewModelStoreOwner): VM =
        (application as App).viewModel(model, owner)
}