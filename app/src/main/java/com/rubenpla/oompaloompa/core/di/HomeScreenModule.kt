package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.core.di

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.EmployeesSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeScreenModule {

    @Provides
    fun providesEmployeesSource(employeeRepository: EmployeeRepository) =
        EmployeesSource(employeeRepository)
}