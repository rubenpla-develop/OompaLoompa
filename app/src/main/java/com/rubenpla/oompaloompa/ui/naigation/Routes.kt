package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.naigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("HomeScreen")
    object EmployeeProfile : Routes("EmployeeProfile?id={id}") {
        fun createRoute(id : String) = "EmployeeProfile?id=$id"
    }
}

const val EMPLOYEE_ID_PARAM_NAME = "id"
const val DEFAULT_EMPLOYEE_ID = 1
