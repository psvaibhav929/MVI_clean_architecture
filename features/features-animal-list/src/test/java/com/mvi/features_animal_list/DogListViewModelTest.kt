package com.mvi.features_animal_list

import test.MainCoroutinesRule
import com.mvi.domain.usecase.GetDogBreedsUseCase
import com.mvi.features_animal_list.mockdata.fetchDogBreedsMockData
import com.mvi.features_animal_list.intent.DogListIntent
import com.mvi.features_animal_list.viewmodel.DogListViewModel
import com.mvi.features_animal_list.viewstate.DogListViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogListViewModel = DogListViewModel(getDogBreedsUseCase, UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `getDogBreeds success`() = runTest {
        // Arrange
        val fakeDogList = fetchDogBreedsMockData()
        coEvery { getDogBreedsUseCase() } returns Result.success(fakeDogList)

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
        val errorMessage = "Error fetching dog breeds"
        coEvery { getDogBreedsUseCase() } returns Result.failure(Throwable(errorMessage))

        // Act
        dogListViewModel.sendIntent(DogListIntent.GetAnimalList)

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assertTrue(dogListState is DogListViewState.Error)
        assertEquals(errorMessage, (dogListState as DogListViewState.Error).message)
    }
}