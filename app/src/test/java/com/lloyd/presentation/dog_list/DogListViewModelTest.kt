package com.lloyd.presentation.dog_list


import com.lloyd.MainCoroutinesRule
import com.lloyd.common.Result
import com.lloyd.domain.repository.dto.toDogBreed
import com.lloyd.domain.model.DogBreed
import com.lloyd.domain.usecase.GetDogBreedsUseCase
import com.lloyd.mockdata.fetchDogBreedsMockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogListViewModelTest {

    // Set the main coroutine dispatcher for testing
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutinesRule()

    private val getDogBreedsUseCase: com.lloyd.domain.usecase.GetDogBreedsUseCase = mockk(relaxed = true)
    private lateinit var dogListViewModel: DogListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogListViewModel = DogListViewModel(getDogBreedsUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds success`() = runTest {
        // Arrange
        val fakeDogList = fetchDogBreedsMockData().toDogBreed()
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Success(fakeDogList)))

        // Act
        dogListViewModel.getDogBreeds()

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assertEquals(fakeDogList.dogs, dogListState.dogBreeds)
        assertEquals(false, dogListState.isLoading)
        assertNull(dogListState.error)
    }

    @Test
    fun `getDogBreeds error`() = runTest {
        // Arrange
        val errorMessage = "An unexpected error"
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Error(errorMessage)))

        // Act
        dogListViewModel.getDogBreeds()

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assert(dogListState.dogBreeds.isEmpty())
        assertEquals(false, dogListState.isLoading)
        assertEquals(errorMessage, dogListState.error)
    }

    @Test
    fun `getDogBreeds loading`() = runTest {
        // Arrange
        coEvery {getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Loading<com.lloyd.domain.model.DogBreed>()))

        // Act
        dogListViewModel.getDogBreeds()

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assert(dogListState.dogBreeds.isEmpty())
        assertEquals(true, dogListState.isLoading)
        assertNull(dogListState.error)
    }
}