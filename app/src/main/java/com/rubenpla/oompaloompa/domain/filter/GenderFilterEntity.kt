package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter

data class GenderFilterEntity(
    val id: Int,
    val name: String,
    val value : String,
    var isSelected: Boolean = false
) : Filter {
    override fun getFilterName() : String {
       return name
    }
}
