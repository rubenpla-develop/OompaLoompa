package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.ViewIntent

sealed class HomeIntent: ViewIntent {
    object AllEmployees: HomeIntent()
}
