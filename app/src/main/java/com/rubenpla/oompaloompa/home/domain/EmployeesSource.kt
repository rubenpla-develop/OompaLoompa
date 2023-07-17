package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter.FilterType
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter.EmployeeFiltersEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeeResultsEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.repository.EmployeeRepository
import javax.inject.Inject

class EmployeesSource @Inject constructor(private val employeeRepository: EmployeeRepository) :
    PagingSource<Int, EmployeeResultsEntity>() {

    private var professionalFilter : EmployeeFiltersEntity? = EmployeeFiltersEntity(FilterType.NONE, "")
    override fun getRefreshKey(state: PagingState<Int, EmployeeResultsEntity>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EmployeeResultsEntity> {

        val nextPage = params.key ?: 1
        val employeesResponse = employeeRepository.getEmployess(nextPage)

        return if (employeesResponse.data == null) {
            LoadResult.Error(Exception(employeesResponse.error.toString()))
        } else {
            val filteredList = if (professionalFilter?.type != FilterType.NONE) {
                employeesResponse.data.employeeEntities.filter { professionalItem ->
                    when (professionalFilter?.type) {
                        FilterType.PROFESSION -> { professionalItem.profession == professionalFilter?.selectedFilters }
                        FilterType.GENDER -> { professionalItem.gender == professionalFilter?.selectedFilters }
                        else -> { true }
                    }
                }
            } else {
                employeesResponse.data.employeeEntities
            }

            LoadResult.Page(
                data = filteredList,
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = nextPage.plus(1)
            )
        }
    }

    fun setEmployeeFilter(filters: HashMap<FilterType, String>?) {
        filters?.let {
            val filterType = filters.keys.firstOrNull()
            professionalFilter = EmployeeFiltersEntity(type = filterType, selectedFilters = filters[filterType])
        }
    }
}