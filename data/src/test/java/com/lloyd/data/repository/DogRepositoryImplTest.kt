package com.lloyd.data.repository

import com.lloyd.data.mockdata.fetchDogBreedsMockData
import com.lloyd.data.mockdata.fetchDogDetailsMockData
import com.lloyd.data.remote.DogApi
import com.lloyd.domain.repository.DogRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class DogRepositoryImplTest {
    private var dogApi: DogApi = mockk()
    private lateinit var dogRepository: DogRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogRepository = DogRepositoryImpl(dogApi)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds should return DogBreedDto`() = runTest {
        // Arrange
        val fakeDogBreedDto = fetchDogBreedsMockData()
        coEvery { dogApi.getAllBreeds() } returns fakeDogBreedDto

        // Act
        val result = dogRepository.getDogBreeds()

        // Assert
        assert(result == fakeDogBreedDto)
    }

    @Test
    fun `getDogDetailsByBreedName should return DogDetailsDto`() = runTest {
        // Arrange
        val dogBreedName = "affenpinscher"
        val fakeDogDetailsDto = fetchDogDetailsMockData()
        coEvery { dogApi.getDogDetailsByBreedName(dogBreedName) } returns fakeDogDetailsDto

        // Act
        val result = dogRepository.getDogDetailsByBreedName(dogBreedName)

        // Assert
        assert(result == fakeDogDetailsDto)
    }
}