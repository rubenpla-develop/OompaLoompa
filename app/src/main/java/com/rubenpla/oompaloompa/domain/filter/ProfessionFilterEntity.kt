package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter

data class ProfessionFilterEntity(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
) : Filter {
    override fun getFilterName() : String {
        return name
    }
}
