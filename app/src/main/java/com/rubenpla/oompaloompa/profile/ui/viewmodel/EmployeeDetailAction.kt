package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.ViewAction

sealed class EmployeeDetailAction: ViewAction {
    object GetEmployeeDetail: EmployeeDetailAction()
}
