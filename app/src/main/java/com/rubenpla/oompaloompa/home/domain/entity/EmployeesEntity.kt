package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity

import com.google.gson.annotations.SerializedName

data class EmployeesEntity(
    val employeeEntities : List<EmployeeResultsEntity>
)

data class EmployeeResultsEntity (
    @SerializedName("id")
    val id: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("profession")
    val profession: String,
    @SerializedName("image")
    val image: String? = null
)
