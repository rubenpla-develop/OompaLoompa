package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response

import com.google.gson.annotations.SerializedName
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeeResultsEntity

data class EmployeeListResponse(
    @SerializedName("current")
    val currentPage: Int,
    @SerializedName("total")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<EmployeeShortInfo>
)

data class EmployeeShortInfo(
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

fun EmployeeShortInfo.toEntity() = EmployeeResultsEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    gender = toHumanGender(gender),
    profession = profession,
    image = image
)

fun List<EmployeeShortInfo>.toEntity() = map { it.toEntity() }

fun EmployeeShortInfo.toHumanGender(shortGender : String? = "U") : String =
    when (shortGender) {
        "M" -> "Male"
        "F" -> "Female"
        else -> "Unknown"
    }