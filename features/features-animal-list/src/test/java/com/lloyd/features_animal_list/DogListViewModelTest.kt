package com.lloyd.features_animal_list

import com.lloyd.common.Result
import com.lloyd.domain.usecase.GetDogBreedsUseCase
import com.lloyd.features_animal_list.mockdata.fetchDogBreedsMockData
import com.lloyd.features_animal_list.intent.DogListIntent
import com.lloyd.features_animal_list.viewmodel.DogListViewModel
import com.lloyd.features_animal_list.viewstate.DogListViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogListViewModelTest {

    // Set the main coroutine dispatcher for testing
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutinesRule()

    private val getDogBreedsUseCase: GetDogBreedsUseCase =
        mockk(relaxed = true)
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
        val fakeDogList = fetchDogBreedsMockData()
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Success(fakeDogList)))

        // Act
        dogListViewModel.sendIntent(DogListIntent.GetAnimalList)

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assertTrue(dogListState is DogListViewState.Success)
        assertEquals(fakeDogList.dogs, (dogListState as DogListViewState.Success).data)
    }

    @Test
    fun `getDogBreeds error`() = runTest {
        // Arrange
        val errorMessage = "An unexpected error"
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Error(errorMessage)))

        // Act
        dogListViewModel.sendIntent(DogListIntent.GetAnimalList)

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assertTrue(dogListState is DogListViewState.Error)
        assertEquals(errorMessage, (dogListState as DogListViewState.Error).message)
    }

    @Test
    fun `getDogBreeds loading`() = runTest {
        // Arrange
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Loading()))

        // Act
        dogListViewModel.sendIntent(DogListIntent.GetAnimalList)

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assertTrue(dogListState is DogListViewState.Loading)
    }
}