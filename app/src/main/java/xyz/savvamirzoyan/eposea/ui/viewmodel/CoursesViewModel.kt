package xyz.savvamirzoyan.eposea.ui.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.eposea.domain.interactor.CoursesInteractor
import xyz.savvamirzoyan.eposea.ui.mapper.CourseDomainToUiMapper
import xyz.savvamirzoyan.eposea.ui.model.CourseUi

class CoursesViewModel(
    private val coursesInteractor: CoursesInteractor,
    private val courseDomainToUiMapper: CourseDomainToUiMapper
) : CoreViewModel() {


    private val _coursesFlow = MutableStateFlow<List<CourseUi>>(listOf())
    val coursesFlow: StateFlow<List<CourseUi>> = _coursesFlow

    init {
        fetchCourses()
    }

    fun fetchCourses() {
        viewModelScope.launch {
            _coursesFlow.emit(listOf(CourseUi.Loading))
            val coursesDomain = coursesInteractor.fetchCourses()
            val coursesUi = coursesDomain.map { courseDomainToUiMapper.map(it) }
            _coursesFlow.emit(coursesUi)
        }
    }
}