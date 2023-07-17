package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter.FilterType
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter.GenderFilterEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter.ProfessionFilterEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.EmployeesSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.ApiConstants
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(private val employeesSource: EmployeesSource) :
    BaseViewModel<HomeIntent, HomeAction, HomeState>() {

    private var employeeFilters: HashMap<FilterType, String>? = hashMapOf()

    override fun createInitialState(): HomeState = HomeState.InitialState

    fun dispatchIntentWithFilters(intent: HomeIntent, filterCateGory : FilterType, filterName : String) {
        employeeFilters?.clear()

        val filtersMap = hashMapOf<FilterType, String>()
        filtersMap[filterCateGory] = filterName
        employeeFilters?.putAll(filtersMap)

        handleAction(mapIntentToAction(intent = intent))
    }

    override fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.GetAllEmployees -> getAllEmployees()
            is HomeAction.GetFilteredEmployees -> getAllEmployees(true)
            else -> {}
        }
    }

    override fun mapIntentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.AllEmployees -> HomeAction.GetAllEmployees
            is HomeIntent.GetFilteredEmployees -> HomeAction.GetFilteredEmployees
            else -> HomeAction.GetAllEmployees
        }
    }


    private fun getAllEmployees(shouldApplyFilters : Boolean = false) {
        if (shouldApplyFilters) employeesSource.setEmployeeFilter(employeeFilters)
        val employees = Pager(PagingConfig(ApiConstants.PAGE_SIZE)) {
            employeesSource
        }

        setState(HomeState.EmployeeListData(employees = employees.flow))
    }

    fun getProfessionsFilter(): List<ProfessionFilterEntity> {
        return listOf(
            ProfessionFilterEntity(
                id = 1,
                name = "Developer",
            ),
            ProfessionFilterEntity(
                id = 2,
                name = "Medic",
            ),
            ProfessionFilterEntity(
                id = 3,
                name = "Metalworker",
            ),
            ProfessionFilterEntity(
                id = 4,
                name = "Gemcutter",
            ),
            ProfessionFilterEntity(
                id = 5,
                name = "Brewer",
            )
        )
    }

    fun getGenderFilters(): List<GenderFilterEntity> {
        return listOf(
            GenderFilterEntity(
                id = 1,
                name = "Male",
                value = "M"
            ),
            GenderFilterEntity(
                id = 2,
                name = "Female",
                value = "F"
            )
        )
    }
}