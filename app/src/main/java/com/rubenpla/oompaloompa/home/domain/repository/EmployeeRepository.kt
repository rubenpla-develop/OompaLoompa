package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeesEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record

interface EmployeeRepository {

    suspend fun getEmployess(nextPage: Int) : Record<EmployeesEntity>
}