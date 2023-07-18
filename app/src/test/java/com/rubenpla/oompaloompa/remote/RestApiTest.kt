package com.rubenpla.oompaloompa.remote

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.request.GetAllEmployeesRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.remote.response.EmployeeListResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.request.GetEmployeeDetailRequest
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.remote.response.EmployeeDetailResponse
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.RemoteException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection


class RestApiTest: BaseTest() {

    @Test
    fun should_get_all_games_when_server_gives_success_response() = runBlocking {
        val expectedResponse = getExpectedResponse("get_all_employees_success_response.json", EmployeeListResponse::class.java)
        getResponse("get_all_employees_success_response.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getEmployees(GetAllEmployeesRequest(1))
        Assert.assertEquals(expectedResponse.results.size, result.results.size)
    }

    @Test
    fun should_get_game_details_when_server_gives_success_response() = runBlocking {
        val expectedResponse = getExpectedResponse("get_employee_details_success_response.json", EmployeeDetailResponse::class.java)
        getResponse("get_employee_details_success_response.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getEmployeeDetail(GetEmployeeDetailRequest(1))
        //TODO Because there's no id in employee detail's json response, email is the best option for
        //TODO an 'unique id'
        Assert.assertEquals(expectedResponse.email, result.email)
    }

    @Test
    fun should_throw_client_exception_when_server_sends_4xx_response() {
        Assert.assertThrows(RemoteException.ClientError::class.java) {
            runBlocking {
                getResponse("get_all_employees_success_response.json", HttpURLConnection.HTTP_BAD_REQUEST)
                restApi.getEmployees(GetAllEmployeesRequest(1))
            }
        }
    }

    @Test
    fun should_throw_server_exception_when_server_sends_5xx_response() {
        Assert.assertThrows(RemoteException.ServerError::class.java) {
            runBlocking {
                getResponse("get_all_employees_success_response.json", HttpURLConnection.HTTP_BAD_GATEWAY)
                restApi.getEmployees(GetAllEmployeesRequest(1))
            }
        }
    }

    @Test
    fun should_throw_no_network_exception_in_case_of_timeout() {
        Assert.assertThrows(RemoteException.NoNetworkError::class.java) {
            runBlocking {
                getTimeout()
                restApi.getEmployees(GetAllEmployeesRequest(1))
            }
        }
    }
}