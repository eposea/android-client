package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelsFactory(
    private val institutionViewModel: InstitutionViewModel
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        InstitutionViewModel::class.java -> institutionViewModel as T
        else -> throw IllegalArgumentException("Unknown viewmodel $modelClass")
    }
}