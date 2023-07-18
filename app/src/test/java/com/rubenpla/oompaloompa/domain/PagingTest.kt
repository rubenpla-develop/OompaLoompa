package com.rubenpla.oompaloompa.domain

import androidx.paging.PagingSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.ErrorRecord
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.EmployeesSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository.EmployeeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PagingTest {

    private val mockRepository = mockk<EmployeeRepository>()

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun should_get_same_amount_of_result_as_specified_in_paging_load() = runBlocking {
        coEvery { mockRepository.getEmployess(any()) } returns Record(FakeData.getEmployeesFakeList(), null)
        val pagingSource = EmployeesSource(mockRepository)
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = FakeData.getEmployeesFakeList().employeeEntities,
                prevKey = null,
                nextKey = 2
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun should_get_error_if_server_gives_error_response() = runBlocking {
        coEvery { mockRepository.getEmployess(any()) } returns Record(null, ErrorRecord.GenericError)
        val pagingSource = EmployeesSource(mockRepository)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 8,
                placeholdersEnabled = false
            )
        )

        Assert.assertTrue(result is PagingSource.LoadResult.Error)
    }
}