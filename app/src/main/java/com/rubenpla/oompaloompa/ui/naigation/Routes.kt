package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.naigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("HomeScreen")
    object EmployeeProfile : Routes("EmployeeProfile?name={id}") {
        fun createRoute(id : String) = "EmployeeProfile?name=$id"
    }
}
