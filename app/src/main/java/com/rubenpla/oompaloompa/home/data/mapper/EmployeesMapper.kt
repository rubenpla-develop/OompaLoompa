package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.mapper

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeesEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response.toEntity

class EmployeesMapper {

    fun mapEmployeesResponse(employeesResponse: EmployeeListResponse): Record<EmployeesEntity> {
        return Record(EmployeesEntity(employeesResponse.results.toEntity()), null)
    }
}