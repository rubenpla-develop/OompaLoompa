package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.EmployeesSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.ApiConstants
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(private val employeesSource: EmployeesSource) :
    BaseViewModel<HomeIntent, HomeAction, HomeState>() {

    override fun createInitialState(): HomeState = HomeState.InitialState

    override fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.GetAllEmployees -> getAllEmployees()
            else -> {}
        }
    }

    override fun mapIntentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.AllEmployees -> HomeAction.GetAllEmployees
            else -> HomeAction.GetAllEmployees
        }
    }


    private fun getAllEmployees() {
        val employees = Pager(PagingConfig(ApiConstants.PAGE_SIZE)) {
            employeesSource
        }

        setState(HomeState.EmployeeListData(employees = employees.flow))
    }
}