package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.core.di

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.DataSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.repository.EmployeeRepositoryImpl
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesEmployeeRepository(dataSource: DataSource): EmployeeRepository =
        EmployeeRepositoryImpl(dataSource = dataSource)

}