package com.rubenpla.oompaloompa.domain

import com.rubenpla.oompaloompa.CoroutinesTestRule
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base.Record
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.GetEmployeeDetailsUseCase
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.repository.EmployeeDetailRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetGameDetailsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockEmployeeDetailRepository = mockk<EmployeeDetailRepository>()
    private val getEmployeeDetailsUseCase = GetEmployeeDetailsUseCase(mockEmployeeDetailRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun should_get_employee_details_entity_from_repository() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockEmployeeDetailRepository.getEmployeeDetails(2) } answers {
            Record(EmployeeDetailEntity(), null)
        }
        getEmployeeDetailsUseCase.invoke(
            coroutinesTestRule.testCoroutineScope,
            coroutinesTestRule.testDispatcher,
            GetEmployeeDetailsUseCase.RequestValue(2)
        ) {
            coVerify { mockEmployeeDetailRepository.getEmployeeDetails(2) }
            confirmVerified(mockEmployeeDetailRepository)
            Assert.assertTrue(it?.data != null)
            Assert.assertTrue(it?.error == null)
        }
    }
}