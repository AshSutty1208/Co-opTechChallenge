package com.trackage.app.ui.character_change

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trackage.app.trackage_api.models.Joke
import com.trackage.app.trackage_api.models.SingleJoke
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.trackage_api.utils.Resource
import com.trackage.app.CoroutineTestRule
import com.trackage.app.util.splitBySpace
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(JUnit4::class)
class CharacterChangeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule()

    private val mockRepository = mock(TrackageRepository::class.java)

    private val viewModel = CharacterChangeViewModel(mockRepository,
        coroutineTestRule.testDispatcherProvider)

    @Test
    fun `Test Initial Values`() = runTest {
        assertEquals(false, viewModel.dialogState.value)
        assertEquals("", viewModel.dialogText.value)
        assertEquals("", viewModel.characterTextValue.value)
    }

    @Test
    fun `Test Fetch Random Joke Success but Data is null`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val characterNameSplit = characterNameFromTextField.splitBySpace()
        val resource = Resource(Resource.Status.SUCCESS, null, null)

        //When
        `when`(mockRepository.getRandomJoke(characterNameSplit[0], characterNameSplit[1]))
            .thenReturn(resource)

        viewModel.onSearchButtonClicked(characterNameFromTextField)

        assertEquals(true, viewModel.dialogState.value)
        assertEquals("THERE WAS AN ERROR GETTING A RANDOM JOKE", viewModel.dialogText.value)
    }

    @Test
    fun `Test Fetch Random Joke Success but Data is good`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val characterNameSplit = characterNameFromTextField.splitBySpace()
        val joke = "Some random joke about chuck norris"
        val data = SingleJoke("success", Joke(123, joke))
        val resource = Resource(Resource.Status.SUCCESS, data, null)

        //When
        `when`(mockRepository.getRandomJoke(characterNameSplit[0], characterNameSplit[1]))
            .thenReturn(resource)

        viewModel.onSearchButtonClicked(characterNameFromTextField)

        assertEquals(true, viewModel.dialogState.value)
        assertEquals(joke, viewModel.dialogText.value)
    }


    @Test
    fun `Test Fetch Random Joke Failed With error msg`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val characterNameSplit = characterNameFromTextField.splitBySpace()
        val errorMsg = "Some random joke has failed"
        val resource = Resource(Resource.Status.ERROR, null, errorMsg)

        //When
        `when`(mockRepository.getRandomJoke(characterNameSplit[0], characterNameSplit[1]))
            .thenReturn(resource)

        viewModel.onSearchButtonClicked(characterNameFromTextField)

        assertEquals(true, viewModel.dialogState.value)
        assertEquals(errorMsg, viewModel.dialogText.value)
    }

    @Test
    fun `Test Fetch Random Joke Failed With null error msg`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val characterNameSplit = characterNameFromTextField.splitBySpace()
        val resource = Resource(Resource.Status.ERROR, null, null)

        //When
        `when`(mockRepository.getRandomJoke(characterNameSplit[0], characterNameSplit[1]))
            .thenReturn(resource)

        viewModel.onSearchButtonClicked(characterNameFromTextField)

        assertEquals(true, viewModel.dialogState.value)
        assertEquals("THERE WAS AN ERROR GETTING A RANDOM JOKE", viewModel.dialogText.value)
    }

    @Test
    fun `Test Text Field Validation With Special Chars`() = runTest {
        //Given
        val characterNameFromTextField = "Joe $£%^&*"

        //When
        viewModel.characterTextValue.postValue(characterNameFromTextField)

        //Then
        viewModel.characterTextInputValid.observeForever {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Test Text Field Validation With One Name`() = runTest {
        //Given
        val characterNameFromTextField = "Joe"

        //When
        viewModel.characterTextValue.postValue(characterNameFromTextField)

        //Then
        viewModel.characterTextInputValid.observeForever {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Test Text Field Validation With One Name and space`() = runTest {
        //Given
        val characterNameFromTextField = "Joe "

        //When
        viewModel.characterTextValue.postValue(characterNameFromTextField)

        //Then
        viewModel.characterTextInputValid.observeForever {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Test Text Field Validation With Numbers`() = runTest {
        //Given
        val characterNameFromTextField = "Joe 123"

        //When
        viewModel.characterTextValue.postValue(characterNameFromTextField)

        //Then
        viewModel.characterTextInputValid.observeForever {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Test Text Field Validation With Correct Value`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"

        //When
        viewModel.characterTextValue.postValue(characterNameFromTextField)

        //Then
        viewModel.characterTextInputValid.observeForever {
            assertEquals(true, it)
        }
    }
}

