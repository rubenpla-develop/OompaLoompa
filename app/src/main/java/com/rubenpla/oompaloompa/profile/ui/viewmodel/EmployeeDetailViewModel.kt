package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.GetEmployeeDetailsUseCase
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.BaseViewModel
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.naigation.DEFAULT_EMPLOYEE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class EmployeeDetailViewModel @Inject constructor(
    private val getEmployeeDetailsUseCase: GetEmployeeDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BaseViewModel<EmployeeDetailIntent, EmployeeDetailAction, EmployeeDetailState>() {

    private var employeeId = DEFAULT_EMPLOYEE_ID

    override fun createInitialState(): EmployeeDetailState = EmployeeDetailState.InitialState

    override fun handleAction(action: EmployeeDetailAction) {
        when (action) {
            is EmployeeDetailAction.GetEmployeeDetail -> {
                getEmployeeDetail(employeeId)
            }

            else -> {}
        }
    }

    override fun mapIntentToAction(intent: EmployeeDetailIntent): EmployeeDetailAction {
        return when (intent) {
            is EmployeeDetailIntent.EmployeeDetail -> EmployeeDetailAction.GetEmployeeDetail
            else -> EmployeeDetailAction.GetEmployeeDetail
        }
    }

    fun setEmployeeId(id : Int) {
        employeeId = id
    }


    private fun getEmployeeDetail(employeeId: Int) {
        getEmployeeDetailsUseCase.invoke(
            viewModelScope,
            dispatcher,
            GetEmployeeDetailsUseCase.RequestValue(employeeId = employeeId)
        ) { record ->
            setState(EmployeeDetailState.EmployeeDetailData(employeeDetail = record?.data))
        }
    }
}