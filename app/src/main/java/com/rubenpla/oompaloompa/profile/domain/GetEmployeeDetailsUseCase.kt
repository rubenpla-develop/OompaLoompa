package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.base.BaseUseCase
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.repository.EmployeeDetailRepository
import javax.inject.Inject

class GetEmployeeDetailsUseCase @Inject constructor(private val employeeDetailRepository: EmployeeDetailRepository) :
    BaseUseCase<GetEmployeeDetailsUseCase.RequestValue, Record<EmployeeDetailEntity>>() {

    override suspend fun run(request: RequestValue): Record<EmployeeDetailEntity> {
        return employeeDetailRepository.getEmployeeDetails(request.employeeId)
    }

    data class RequestValue(
        val employeeId: Int
    )
}