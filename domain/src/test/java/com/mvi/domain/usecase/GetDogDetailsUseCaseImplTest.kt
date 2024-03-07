package com.mvi.domain.usecase

import com.mvi.domain.mockdata.fetchDogDetailsMockData
import com.mvi.domain.repository.DogRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
            GetDogDetailsUseCase(dogRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogDetailsByBreedName success`() = runTest {
        // Arrange
        val fakeDogDetails = fetchDogDetailsMockData()
        val dogBreedName = "affenpinscher"
        coEvery { dogRepository.getDogDetailsByBreedName(dogBreedName) } returns Result.success(fakeDogDetails)

        // Act
        val result = getDogDetailsUseCase(dogBreedName)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(fakeDogDetails, result.getOrNull())
    }
}