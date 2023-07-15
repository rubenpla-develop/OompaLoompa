package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.retrofit

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.response.EmployeeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("oompa-loompas")
    suspend fun getEmployeesList(
        @Query("page") page: Int,
    ): EmployeeListResponse

}