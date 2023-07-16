package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.request.GetAllEmployeesRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.request.GetEmployeeDetailRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response.EmployeeDetailResponse

interface RestApi {
    suspend fun getEmployees(getAllEmployeesRequest: GetAllEmployeesRequest): EmployeeListResponse

    suspend fun getEmployeeDetail(getEmployeeDetailRequest: GetEmployeeDetailRequest): EmployeeDetailResponse

}