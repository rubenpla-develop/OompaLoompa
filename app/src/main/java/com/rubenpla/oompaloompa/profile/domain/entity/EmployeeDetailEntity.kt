package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity

data class EmployeeDetailEntity(
    val firstName: String? = "",
    val lastName: String? = "",
    val description: String? = "",
    val image: String? = "",
    val profession: String? = "",
    val quota: String? = "",
    val height: Int? = 0,
    val country: String? = "",
    val age: Int = 0,
    val favorites: EmployeeFavoritesEntity,
    val gender: String = "",
    val email: String? = ""
)

data class EmployeeFavoritesEntity(
    val color: String? = "",
    val food: String? = "",
    val randomText: String? = "",
    val song: String? = ""
)
