package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.repository

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity

interface EmployeeDetailRepository {
    suspend fun getEmployeeDetails(employeeId : Int) : Record<EmployeeDetailEntity>
}