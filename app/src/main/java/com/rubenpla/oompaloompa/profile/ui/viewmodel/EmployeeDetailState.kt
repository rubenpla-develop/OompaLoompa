package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.ViewState

sealed class EmployeeDetailState: ViewState {

    object InitialState: EmployeeDetailState()
    object LoadingState: EmployeeDetailState()
    class EmployeeDetailData(val employeeDetail: EmployeeDetailEntity?): EmployeeDetailState()
}
