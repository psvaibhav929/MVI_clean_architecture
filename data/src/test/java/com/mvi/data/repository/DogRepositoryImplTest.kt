package com.mvi.data.repository

import com.mvi.common.ApiResult
import com.mvi.data.mappers.DogMappers
import com.mvi.data.mockdata.fetchDogBreedsMockData
import com.mvi.data.mockdata.fetchDogDetailsMockData
import com.mvi.data.services.DogService
import com.mvi.domain.repository.DogRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DogRepositoryImplTest {
    private var dogApi: DogService = mockk()
    private lateinit var dogMappers: DogMappers
    private lateinit var dogRepository: DogRepository



    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogMappers = DogMappers()
        dogRepository = DogRepositoryImpl(dogApi,dogMappers)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds should return DogBreed`() = runTest {
        // Arrange
        val fakeDogBreedDto = fetchDogBreedsMockData()
        coEvery { dogApi.getAllBreeds() } returns Response.success(fakeDogBreedDto)

        // Act
        val result = dogRepository.getDogBreeds()

        // Assert
        assert(result is ApiResult.Success)
        assertEquals(dogMappers.toDogBreed(fakeDogBreedDto), (result as ApiResult.Success).data)
    }

    @Test
    fun `getDogDetailsByBreedName should return DogDetails`() = runTest {
        // Arrange
        val dogBreedName = "affenpinscher"
        val fakeDogDetailsDto = fetchDogDetailsMockData()
        coEvery { dogApi.getDogDetailsByBreedName(dogBreedName) } returns Response.success(
            fakeDogDetailsDto
        )

        // Act
        val result = dogRepository.getDogDetailsByBreedName(dogBreedName)

        // Assert
        assert(result is ApiResult.Success)
        assertEquals(dogMappers.toDogDetails(fakeDogDetailsDto), (result as ApiResult.Success).data)
    }
}