package com.mvi.domain.usecase

import com.mvi.domain.mockdata.fetchDogDetailsMockData
import com.mvi.domain.repository.DogRepository
import com.mvi.domain.result.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetDogDetailsUseCaseImplTest {
    private var dogRepository: DogRepository = mockk()
    private lateinit var getDogDetailsUseCase: GetDogDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

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

        coEvery { dogRepository.getDogDetailsByBreedName(DOG_BREED_NAME) } returns ApiResult.Success(
            fakeDogDetails
        )

        // Act
        val result = getDogDetailsUseCase(DOG_BREED_NAME)

        // Assert
        assertTrue(result is ApiResult.Success)
        assertEquals(fakeDogDetails, (result as ApiResult.Success).data)
    }

    @Test
    fun `getDogDetailsByBreedName error`() = runTest {
        // Arrange
        val errorMessage = ERROR_MESSAGE

        coEvery { dogRepository.getDogDetailsByBreedName(DOG_BREED_NAME) } returns ApiResult.Error(
            Throwable(errorMessage)
        )

        // Act
        val result = getDogDetailsUseCase(DOG_BREED_NAME)

        // Assert
        assertTrue(result is ApiResult.Error)
        assertEquals(errorMessage, (result as ApiResult.Error).exception?.message)
    }

    companion object {
        private const val DOG_BREED_NAME = "affenpinscher"
        private const val ERROR_MESSAGE = "Error fetching dog details"
    }
}