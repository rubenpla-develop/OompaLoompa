package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.retrofit

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response.EmployeeDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {
    @GET("oompa-loompas")
    suspend fun getEmployeesList(
        @Query("page") page: Int,
    ): EmployeeListResponse

    @GET("oompa-loompas/{id}")
    suspend fun getEmployeeDetail(
        @Path("id") employeeId: Int,
    ): EmployeeDetailResponse

}