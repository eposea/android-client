package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.R
import xyz.savvamirzoyan.eposea.domain.interactor.InstitutionInfoInteractor
import xyz.savvamirzoyan.eposea.ui.ResourceManager
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionInfoDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.mapper.InstitutionInfoToolbarDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoToolbarUi
import xyz.savvamirzoyan.eposea.ui.model.InstitutionInfoUi

class InstitutionInfoViewModel(
    private val institutionInfoInteractor: InstitutionInfoInteractor,
    private val institutionInfoToolbarDomainToUiMapper: InstitutionInfoToolbarDomainToUiMapper,
    private val institutionInfoDomainToUiMapper: InstitutionInfoDomainToUiMapper,
    private val resourceManager: ResourceManager
) : CoreViewModel() {

    private var institutionId: String = ""

    private val _institutionToolbarInfoFlow =
        MutableStateFlow<InstitutionInfoToolbarUi>(InstitutionInfoToolbarUi.Loading(resourceManager.getString(R.string.loading)))
    val institutionToolbarInfoFlow: StateFlow<InstitutionInfoToolbarUi> = _institutionToolbarInfoFlow

    private val _institutionInfoFlow = MutableStateFlow<List<InstitutionInfoUi>>(listOf(InstitutionInfoUi.Loading))
    val institutionInfoFlow: StateFlow<List<InstitutionInfoUi>> = _institutionInfoFlow

    fun fetchInstitutionInfo(_institutionId: String) {
        this.institutionId = _institutionId

        viewModelScope.launch {
            val institutionInfoDomain = institutionInfoInteractor.fetchInstitutionInfo(institutionId)
            val institutionInfoToolbarUi = institutionInfoToolbarDomainToUiMapper.map(institutionInfoDomain)
            val institutionInfoUi = institutionInfoDomainToUiMapper.map(institutionInfoDomain)

            _institutionToolbarInfoFlow.emit(institutionInfoToolbarUi)
            _institutionInfoFlow.emit(institutionInfoUi)
        }
    }

    fun onRetry() {
        viewModelScope.launch {
            _institutionToolbarInfoFlow.emit(InstitutionInfoToolbarUi.Loading(resourceManager.getString(R.string.loading)))
            _institutionInfoFlow.emit(listOf(InstitutionInfoUi.Loading))

            val institutionInfoDomain = institutionInfoInteractor.fetchInstitutionInfo(institutionId)
            val institutionInfoToolbarUi = institutionInfoToolbarDomainToUiMapper.map(institutionInfoDomain)
            val institutionInfoUi = institutionInfoDomainToUiMapper.map(institutionInfoDomain)

            _institutionToolbarInfoFlow.emit(institutionInfoToolbarUi)
            _institutionInfoFlow.emit(institutionInfoUi)
        }
    }
}