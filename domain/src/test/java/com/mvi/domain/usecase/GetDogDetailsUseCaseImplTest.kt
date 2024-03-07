package com.mvi.domain.usecase

import com.mvi.common.ApiResult
import com.mvi.domain.mockdata.fetchDogDetailsMockData
import com.mvi.domain.model.DogDetails
import com.mvi.domain.repository.DogRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetDogDetailsUseCaseImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private var dogRepository: DogRepository = mockk()
    private lateinit var getDogDetailsUseCase: GetDogDetailsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)

        getDogDetailsUseCase =
            GetDogDetailsUseCase(dogRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogDetailsByBreedName success`() = runTest {
        // Arrange
        val fakeDogDetails = fetchDogDetailsMockData()
        coEvery { dogRepository.getDogDetailsByBreedName("affenpinscher") } returns ApiResult.Success(fakeDogDetails)

        // Act
        val apiResult: MutableList<ApiResult<DogDetails>> = mutableListOf()
        val flow: Flow<ApiResult<DogDetails>> = getDogDetailsUseCase("affenpinscher")

        // Assert
        flow.collect {
            apiResult.add(it)
        }

        assertEquals(2, apiResult.size) // Loading + Success
        assert(apiResult[0] is ApiResult.Loading)
        assert(apiResult[1] is ApiResult.Success)
        assert(apiResult[1].data?.dogImageUrl?.isNotBlank() == true)
    }


}