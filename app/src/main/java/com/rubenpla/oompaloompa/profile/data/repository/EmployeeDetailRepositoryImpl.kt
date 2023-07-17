package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.data.repository


import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data.DataSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data.mapper.ErrorMapper
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.data.mapper.EmployeeDetailMapper
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.repository.EmployeeDetailRepository
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.request.GetEmployeeDetailRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.RemoteException
import javax.inject.Inject

class EmployeeDetailRepositoryImpl @Inject constructor(private val dataSource: DataSource) :
    EmployeeDetailRepository {

    private val employeeDetailMapper = EmployeeDetailMapper()
    private val errorMapper = ErrorMapper()

    override suspend fun getEmployeeDetails(employeeId: Int): Record<EmployeeDetailEntity> {
        return try {
            dataSource.api().restApi().getEmployeeDetail(GetEmployeeDetailRequest(employeeId)).run {
                employeeDetailMapper.mapEmployeeDetailResponse(this)
            }
        } catch (remoteException: RemoteException) {
            errorMapper.mapErrorRecord(remoteException)
        }
    }
}