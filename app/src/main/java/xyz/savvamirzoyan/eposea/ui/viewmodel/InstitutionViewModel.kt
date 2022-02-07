package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.domain.interactor.InstitutionInteractor
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionDomainToWithCoursesUiMapper
import xyz.savvamirzoyan.eposea.ui.model.InstitutionWithCoursesUi

class InstitutionViewModel(
    private val interactor: InstitutionInteractor,
    private val institutionDomainToWithCoursesUiMapper: InstitutionDomainToWithCoursesUiMapper
) : CoreViewModel() {

    private val _institutionsStateFlow = MutableStateFlow<List<InstitutionWithCoursesUi>>(listOf())
    val institutionsStateFlow: StateFlow<List<InstitutionWithCoursesUi>> = _institutionsStateFlow

    init {
        fetchInstitutions()
    }

    private fun fetchInstitutions() {
        viewModelScope.launch {
            _institutionsStateFlow.emit(listOf(InstitutionWithCoursesUi.Loading))
            val institutionWithCoursesUi = institutionDomainToWithCoursesUiMapper.map(interactor.fetchInstitutions())
            _institutionsStateFlow.emit(institutionWithCoursesUi)
        }
    }

    fun onRetry() {
        fetchInstitutions()
    }
}