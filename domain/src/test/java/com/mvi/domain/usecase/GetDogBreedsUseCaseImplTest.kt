package com.mvi.domain.usecase

import com.mvi.domain.mockdata.fetchDogBreedsMockData
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


class GetDogBreedsUseCaseImplTest {
    private var dogRepository: DogRepository = mockk()
    private lateinit var getDogBreedsUseCase: GetDogBreedsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        getDogBreedsUseCase =
            GetDogBreedsUseCase(dogRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds success`() = runTest {
        // Arrange
        val fakeDogBreeds = fetchDogBreedsMockData()

        coEvery { dogRepository.getDogBreeds() } returns ApiResult.Success(fakeDogBreeds)

        // Act
        val result = getDogBreedsUseCase()

        // Assert
        assertTrue(result is ApiResult.Success)
        assertEquals(fakeDogBreeds, (result as ApiResult.Success).data)
    }

    @Test
    fun `getDogBreeds error`() = runTest {
        // Arrange
        val errorMessage = ERROR_MESSAGE
        coEvery { dogRepository.getDogBreeds() } returns ApiResult.Error(Throwable(errorMessage))

        // Act
        val result = getDogBreedsUseCase()

        // Assert
        assertTrue(result is ApiResult.Error)
        assertEquals(errorMessage, (result as ApiResult.Error).exception?.message)
    }

    companion object {
        const val ERROR_MESSAGE = "Error fetching dog breeds"
    }

}