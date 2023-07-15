package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.request.GetAllEmployeesRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.retrofit.RetrofitApi
import javax.inject.Inject

class RestApiImpl @Inject constructor(private val retrofitApi: RetrofitApi): RestApi {

    override suspend fun getEmployees(getAllEmployeesRequest: GetAllEmployeesRequest): EmployeeListResponse {
        return retrofitApi.getEmployeesList(getAllEmployeesRequest.nextPage)
    }
}