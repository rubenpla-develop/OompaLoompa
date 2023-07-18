package com.rubenpla.oompaloompa.compose.utils

import androidx.paging.PagingData
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeeResultsEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object EmployeesFakeData {

    fun getFakePagingData(): Flow<PagingData<EmployeeResultsEntity>> {
        return flow {
            emit(
                PagingData.from(employees().employeeEntities)
            )
        }
    }

    private fun employees() = EmployeesEntity(getEmployeeEntity())

    private fun getEmployeeEntity(): List<EmployeeResultsEntity> {
        return listOf(
            EmployeeResultsEntity(
                id = "1",
                firstName = "The Witcher",
                lastName = "Geralt",
                gender = "Male",
                profession = "Developer",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "2",
                firstName = "Spiderman",
                lastName = "Geralt",
                gender = "Female",
                profession = "Brewer",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "3",
                firstName = "Wolverine",
                lastName = "Geralt",
                gender = "Male",
                profession = "Developer",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "4",
                firstName = "Batman",
                lastName = "Geralt",
                gender = "Male",
                profession = "Developer",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "5",
                firstName = "Thor",
                lastName = "Geralt",
                gender = "Male",
                profession = "Gemcutter",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "6",
                firstName = "Flash",
                lastName = "Geralt",
                gender = "Female",
                profession = "Medic",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "7",
                firstName = "Green Lantern",
                lastName = "Geralt",
                gender = "Male",
                profession = "Metalworker",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            ),
            EmployeeResultsEntity(
                id = "8",
                firstName = "Wonder Woman",
                lastName = "Geralt",
                gender = "Female",
                profession = "Developer",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg"
            )
        )
    }
}