package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.mapper

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.response.toEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeesEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.base.Record

class EmployeesMapper {

    fun mapEmployeesResponse(employeesResponse: EmployeeListResponse): Record<EmployeesEntity> {
        return Record(EmployeesEntity(employeesResponse.results.toEntity()), null)
    }
}