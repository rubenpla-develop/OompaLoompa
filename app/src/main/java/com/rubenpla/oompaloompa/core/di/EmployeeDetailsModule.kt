package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.core.di

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.GetEmployeeDetailsUseCase
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.repository.EmployeeDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object EmployeeDetailsModule {

    @Provides
    fun providesGetEmployeeDetailUseCase(employeeDetailRepository: EmployeeDetailRepository) =
        GetEmployeeDetailsUseCase(employeeDetailRepository)
}