package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel

import androidx.paging.PagingData
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeeResultsEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.base.ViewState
import kotlinx.coroutines.flow.Flow

sealed class HomeState: ViewState {

    object InitialState: HomeState()
    object LoadingState: HomeState()
    class EmployeeListData(val employees: Flow<PagingData<EmployeeResultsEntity>>): HomeState()
}
