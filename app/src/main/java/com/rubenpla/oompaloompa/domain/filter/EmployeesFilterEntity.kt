package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter

data class EmployeeFiltersEntity(
    val type: FilterType? = FilterType.NONE,
    val selectedFilters: String? = ""
)

enum class FilterType {
    PROFESSION, GENDER, NONE
}