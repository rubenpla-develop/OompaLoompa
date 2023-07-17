package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.ViewAction

sealed class HomeAction: ViewAction {
    object GetAllEmployees: HomeAction()
    object GetFilteredEmployees: HomeAction()
}
