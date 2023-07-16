package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.core.di

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data.DataSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.repository.EmployeeRepositoryImpl
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository.EmployeeRepository
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.data.repository.EmployeeDetailRepositoryImpl
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.repository.EmployeeDetailRepository
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

    @Provides
    fun provideEmployeeDetailRepository(dataSource : DataSource) : EmployeeDetailRepository =
        EmployeeDetailRepositoryImpl(dataSource = dataSource)
}