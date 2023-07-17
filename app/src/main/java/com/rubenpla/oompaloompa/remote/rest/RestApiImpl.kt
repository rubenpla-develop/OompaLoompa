package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.request.GetAllEmployeesRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.request.GetEmployeeDetailRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response.EmployeeDetailResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.retrofit.RetrofitApi
import javax.inject.Inject

class RestApiImpl @Inject constructor(private val retrofitApi: RetrofitApi): RestApi {

    override suspend fun getEmployees(getAllEmployeesRequest: GetAllEmployeesRequest): EmployeeListResponse {
        return retrofitApi.getEmployeesList(getAllEmployeesRequest.nextPage)
    }

    override suspend fun getEmployeeDetail(getEmployeeDetailRequest: GetEmployeeDetailRequest): EmployeeDetailResponse {
        return retrofitApi.getEmployeeDetail(getEmployeeDetailRequest.id)
    }
}