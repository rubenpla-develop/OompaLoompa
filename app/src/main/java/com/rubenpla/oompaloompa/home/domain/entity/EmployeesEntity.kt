package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity

data class EmployeesEntity(
    val employeeEntities : List<EmployeeResultsEntity>
)

data class EmployeeResultsEntity (

    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val profession: String,
    val image: String? = null
)
