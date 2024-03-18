package com.mvi.features_animal_details


import androidx.lifecycle.SavedStateHandle
import com.mvi.domain.result.ApiResult
import com.mvi.domain.usecase.GetDogDetailsUseCase
import com.mvi.features_animal_details.intent.DogDetailsIntent
import com.mvi.features_animal_details.mockdata.fetchDogDetailsMockData
import com.mvi.features_animal_details.viewmodel.DogDetailsViewModel
import com.mvi.features_animal_details.viewstate.DogDetailsViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
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
import test.MainCoroutinesRule

class DogDetailsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutinesRule()

    private val getDogBreedsUseCase: GetDogDetailsUseCase = mockk(relaxed = true)
    private lateinit var dogListViewModel: DogDetailsViewModel

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { savedStateHandle.get<String>("dog_breed_name") } returns dogBreedName
        dogListViewModel =
            DogDetailsViewModel(getDogBreedsUseCase, mainCoroutineRule.testDispatcher, savedStateHandle)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogDetailsByBreedName success`() = runTest {
        // Arrange
        val fakeDogDetails = fetchDogDetailsMockData()


        coEvery { getDogBreedsUseCase(dogBreedName) } returns ApiResult.Success(fakeDogDetails)

        // Act
        dogListViewModel.sendIntent(DogDetailsIntent.GetDogDetails)

        // Assert
        val dogDetailsState = dogListViewModel.dogDetailsState.value
        assertTrue(dogDetailsState is DogDetailsViewState.Success)
        assertEquals(
            fakeDogDetails.dogImageUrl,
            (dogDetailsState as DogDetailsViewState.Success).dogImageUrl
        )
    }

    @Test
    fun `getDogDetailsByBreedName error`() = runTest {
        // Arrange

        coEvery { getDogBreedsUseCase(dogBreedName) } returns ApiResult.Error(Throwable(errorMessage))

        // Act
        dogListViewModel.sendIntent(DogDetailsIntent.GetDogDetails)

        // Assert
        val dogDetailsState = dogListViewModel.dogDetailsState.value
        assertTrue(dogDetailsState is DogDetailsViewState.Error)
        assertEquals(errorMessage, (dogDetailsState as DogDetailsViewState.Error).message)
    }

    private companion object {
        const val dogBreedName = "Labrador"
        const val errorMessage = "An unexpected error"
    }


}