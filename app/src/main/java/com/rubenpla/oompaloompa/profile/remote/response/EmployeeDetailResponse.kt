package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response

import com.google.gson.annotations.SerializedName
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeFavoritesEntity

data class EmployeeDetailResponse (
    @SerializedName("first_name")
    val firstName : String? = "",
    @SerializedName("last_name")
    val lastName : String? = "",
    @SerializedName("description")
    val description : String? = "",
    @SerializedName("image")
    val image : String? = "",
    @SerializedName("profession")
    val profession : String? = "",
    @SerializedName("quota")
    val quota : String? = "",
    @SerializedName("height")
    val height : Int? = 0,
    @SerializedName("country")
    val country : String? = "",
    @SerializedName("age")
    val age : Int = 0,
    @SerializedName("favorite")
    val favorites : EmployeeFavorites,
    @SerializedName("gender")
    val gender : String? = "",
    @SerializedName("email")
    val email : String ? = ""
)

data class EmployeeFavorites(
    @SerializedName("color")
    val color : String? = "",
    @SerializedName("food")
    val food : String? = "",
    @SerializedName("random_string")
    val randomText : String? = "",
    @SerializedName("song")
    val song : String? = ""
)

fun EmployeeDetailResponse.toEntity() = EmployeeDetailEntity(
    firstName = firstName,
    lastName = lastName,
    description = description,
    image = image,
    profession = profession,
    quota = quota,
    height = height,
    country = country,
    age = age,
    favorites = favorites.toEntity(),
    gender = toHumanGender(gender),
    email = email
)

fun EmployeeFavorites.toEntity() = EmployeeFavoritesEntity(
    color = color,
    food = food,
    randomText = randomText,
    song = song
)

fun EmployeeDetailResponse.toHumanGender(shortGender : String? = "U") : String =
    when (shortGender) {
        "M" -> "Male"
        "F" -> "Female"
        else -> "Unknown"
    }
