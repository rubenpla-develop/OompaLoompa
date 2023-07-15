package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.request.GetAllEmployeesRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.response.EmployeeListResponse

interface RestApi {
    suspend fun getEmployees(getAllEmployeesRequest: GetAllEmployeesRequest): EmployeeListResponse

}