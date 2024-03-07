package com.mvi.features_animal_details


 import test.MainCoroutinesRule
 import com.mvi.common.ApiResult
 import com.mvi.domain.usecase.GetDogDetailsUseCase
 import com.mvi.features_animal_details.mockdata.fetchDogDetailsMockData
 import com.mvi.features_animal_details.viewstate.DogDetailsViewState
 import com.mvi.features_animal_details.intent.DogDetailsIntent

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

class DogDetailsViewModelTest {

    // Set the main coroutine dispatcher for testing
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule =  MainCoroutinesRule()

    private val getDogBreedsUseCase: GetDogDetailsUseCase = mockk(relaxed = true)
    private lateinit var dogListViewModel: DogDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogListViewModel = DogDetailsViewModel(getDogBreedsUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogDetailsByBreedName success`() = runTest {
        // Arrange
        val fakeDogDetails = fetchDogDetailsMockData()
        val dogBreedName = "Labrador"
        coEvery { getDogBreedsUseCase(dogBreedName) } returns flowOf(ApiResult.Success(fakeDogDetails))

        // Act
        dogListViewModel.sendIntent(DogDetailsIntent.GetDogDetails(dogBreedName))

        // Assert
        val dogDetailsState = dogListViewModel.dogDetailsState.value
        assertTrue(dogDetailsState is DogDetailsViewState.Success)
        assertEquals(fakeDogDetails.dogImageUrl, (dogDetailsState as DogDetailsViewState.Success).dogImageUrl)
    }

    @Test
    fun `getDogDetailsByBreedName error`() = runTest {
        // Arrange
        val errorMessage = "An unexpected error"
        val dogBreedName = "Labrador"
        coEvery { getDogBreedsUseCase(dogBreedName) } returns flowOf(ApiResult.Error(errorMessage))

        // Act
        dogListViewModel.sendIntent(DogDetailsIntent.GetDogDetails(dogBreedName))

        // Assert
        val dogDetailsState = dogListViewModel.dogDetailsState.value
        assertTrue(dogDetailsState is DogDetailsViewState.Error)
        assertEquals(errorMessage, (dogDetailsState as DogDetailsViewState.Error).message)
    }

    @Test
    fun `getDogDetailsByBreedName loading`() = runTest {
        // Arrange
        val dogBreedName = "Labrador"
        coEvery { getDogBreedsUseCase(dogBreedName) } returns flowOf(ApiResult.Loading())

        // Act
        dogListViewModel.sendIntent(DogDetailsIntent.GetDogDetails(dogBreedName))

        // Assert
        val dogDetailsState = dogListViewModel.dogDetailsState.value
        assertTrue(dogDetailsState is DogDetailsViewState.Loading)
    }
}