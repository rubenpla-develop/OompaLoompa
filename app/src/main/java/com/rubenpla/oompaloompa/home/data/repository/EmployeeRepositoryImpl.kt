package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.repository

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.DataSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.mapper.EmployeesMapper
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.mapper.ErrorMapper
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.request.GetAllEmployeesRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeesEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository.EmployeeRepository
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.RemoteException
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(private val dataSource: DataSource) :
    EmployeeRepository {

    private val employeesMapper = EmployeesMapper()
    private val errorMapper = ErrorMapper()


    override suspend fun getEmployess(nextPage: Int): Record<EmployeesEntity> {
        return try {
            dataSource.api().restApi()
                .getEmployees(getAllEmployeesRequest = GetAllEmployeesRequest(nextPage)).run {
                    employeesMapper.mapEmployeesResponse(this)
                }
        } catch (error : RemoteException) {
            errorMapper.mapErrorRecord(error)
        }
    }
}