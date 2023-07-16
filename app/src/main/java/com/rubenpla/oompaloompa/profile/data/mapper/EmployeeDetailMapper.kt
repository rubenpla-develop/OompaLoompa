package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.data.mapper

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response.EmployeeDetailResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response.toEntity

class EmployeeDetailMapper {
    fun mapEmployeeDetailResponse(employeeDetailResponse: EmployeeDetailResponse): Record<EmployeeDetailEntity> {
        return Record(employeeDetailResponse.toEntity(), null)
    }
}