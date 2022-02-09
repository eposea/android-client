package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.domain.interactor.InstitutionInteractor
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.model.InstitutionUi

class InstitutionViewModel(
    private val interactor: InstitutionInteractor,
    private val institutionDomainToUiMapper: InstitutionDomainToUiMapper
) : CoreViewModel() {

    private val _institutionsStateFlow = MutableStateFlow<List<InstitutionUi>>(listOf())
    val institutionsStateFlow: StateFlow<List<InstitutionUi>> = _institutionsStateFlow

    init {
        onRetry()
    }

    private fun loadInstitutions() {
        viewModelScope.launch {
            _institutionsStateFlow.emit(listOf(InstitutionUi.Loading))
            val institutionWithCoursesUi = institutionDomainToUiMapper.map(interactor.fetchInstitutions())
            _institutionsStateFlow.emit(institutionWithCoursesUi)
        }
    }

    fun onRetry() {
        loadInstitutions()
    }
}