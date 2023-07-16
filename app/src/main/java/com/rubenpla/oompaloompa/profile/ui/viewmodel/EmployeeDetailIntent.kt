package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.ViewIntent

sealed class EmployeeDetailIntent: ViewIntent {
    object EmployeeDetail: EmployeeDetailIntent()
}
