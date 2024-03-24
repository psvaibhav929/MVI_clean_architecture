package com.mvi.data.repository

import com.mvi.data.mappers.DogDetailsMappers
import com.mvi.data.mappers.DogListMappers
import com.mvi.data.mockdata.fetchDogBreedsMockData
import com.mvi.data.mockdata.fetchDogDetailsMockData
import com.mvi.data.services.DogService
import com.mvi.domain.repository.DogRepository
import com.mvi.domain.result.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class DogRepositoryImplTest {
    private var dogApi: DogService = mockk()
    private lateinit var dogDetailsMappers: DogDetailsMappers
    private lateinit var dogListMappers: DogListMappers
    private lateinit var dogRepository: DogRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogDetailsMappers = mockk()
        dogListMappers = DogListMappers()
        dogDetailsMappers = DogDetailsMappers()
        dogRepository = DogRepositoryImpl(dogApi, dogDetailsMappers, dogListMappers)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds should return DogBreed`() = runTest {
        // Arrange
        val fakeDogBreedDto = fetchDogBreedsMockData()
        val fakeDogBreed = dogListMappers.toDogBreed(fakeDogBreedDto)

        coEvery { dogApi.getAllBreeds() } returns Response.success(fakeDogBreedDto)
        // Act
        val result = dogRepository.getDogBreeds()

        // Assert
        assertTrue(result is ApiResult.Success)
        assertEquals(fakeDogBreed, (result as ApiResult.Success).data)
    }

    @Test
    fun `getDogDetailsByBreedName should return DogDetails`() = runTest {
        // Arrange

        val fakeDogDetailsDto = fetchDogDetailsMockData()
        val fakeDogDetails = dogDetailsMappers.toDogDetails(fakeDogDetailsDto)

        coEvery { dogApi.getDogDetailsByBreedName(DOG_BREED_NAME) } returns Response.success(
            fakeDogDetailsDto
        )

        // Act
        val result = dogRepository.getDogDetailsByBreedName(DOG_BREED_NAME)

        // Assert
        assertTrue(result is ApiResult.Success)
        assertEquals(fakeDogDetails, (result as ApiResult.Success).data)
    }

    @Test
    fun `getDogDetailsByBreedName should return ApiResult Error`() = runTest {
        // Arrange
        val errorMessage = ERROR_MESSAGE
        coEvery { dogApi.getDogDetailsByBreedName(DOG_BREED_NAME) } throws IOException(errorMessage)

        // Act
        val result = dogRepository.getDogDetailsByBreedName(DOG_BREED_NAME)

        // Assert
        assertTrue(result is ApiResult.Error)
        assertEquals(errorMessage, (result as ApiResult.Error).exception.message)
    }

    companion object {
        private const val DOG_BREED_NAME = "affenpinscher"
        private const val ERROR_MESSAGE = "Couldn't reach server. Check your internet connection."
    }
}
