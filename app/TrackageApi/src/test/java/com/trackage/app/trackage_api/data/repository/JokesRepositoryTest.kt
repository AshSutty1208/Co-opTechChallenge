package com.trackage.app.trackage_api.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.trackage.app.trackage_api.models.Joke
import com.trackage.app.trackage_api.models.SingleJoke
import com.trackage.app.trackage_api.data.remote.JokeRemoteDataSource
import com.trackage.app.trackage_api.utils.Resource
import com.trackage.app.trackage_api.repository.JokesRepository
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
class JokesRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockDataSource = mock(JokeRemoteDataSource::class.java)

    private val repository = JokesRepository(mockDataSource)

    @Test
    fun `Test getRandomJoke() returns error when data is null`() = runTest {
        //Given
        val resource = Resource(Resource.Status.SUCCESS, null, null)

        //When
        `when`(mockDataSource.getRandomJoke())
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke()

        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals("Response Data was null in JokesRepository", returnValue.message)
    }

    @Test
    fun `Test getRandomJoke() is successful`() = runTest {
        //Given
        val joke = "Some random joke about chuck norris"
        val data = SingleJoke("success", Joke(123, joke))
        val resource = Resource(Resource.Status.SUCCESS, data, null)

        //When
        `when`(mockDataSource.getRandomJoke())
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke()

        assertEquals(Resource.Status.SUCCESS, returnValue.status)
        assertEquals(resource, returnValue)
    }

    @Test
    fun `Test getRandomJoke() is unsuccessful with error message`() = runTest {
        //Given
        val errorMsg = "Error when getting joke from api"
        val resource = Resource(Resource.Status.ERROR, null, errorMsg)

        //When
        `when`(mockDataSource.getRandomJoke())
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke()

        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals(errorMsg, returnValue.message)
    }

    @Test
    fun `Test getRandomJoke() is unsuccessful with null error message`() = runTest {
        //Given
        val resource = Resource(Resource.Status.ERROR, null, null)

        //When
        `when`(mockDataSource.getRandomJoke())
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke()

        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals("Error Message Null", returnValue.message)
    }

    @Test
    fun `Test getRandomJoke() is unsuccessful with unknown status`() = runTest {
        //Given
        val resource = Resource(Resource.Status.LOADING, null, null)

        //When
        `when`(mockDataSource.getRandomJoke())
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke()

        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals("Unknown Status in JokesRepository", returnValue.message)
    }

    /**
     * getRandomJoke(firstName, lastName)
     */

    @Test
    fun `Test getRandomJoke(firstName, lastName) returns error when data is null`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val charactersSplit = characterNameFromTextField.split(" ")
        val resource = Resource(Resource.Status.SUCCESS, null, null)

        //When
        `when`(mockDataSource.getRandomJoke(charactersSplit[0], charactersSplit[1]))
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke(charactersSplit[0], charactersSplit[1])

        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals("Response Data was null in JokesRepository", returnValue.message)
    }

    @Test
    fun `Test getRandomJoke(firstName, lastName) is successful`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val charactersSplit = characterNameFromTextField.split(" ")
        val joke = "Some random joke about chuck norris"
        val data = SingleJoke("success", Joke(123, joke))
        val resource = Resource(Resource.Status.SUCCESS, data, null)

        //When
        `when`(mockDataSource.getRandomJoke(charactersSplit[0], charactersSplit[1]))
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke(charactersSplit[0], charactersSplit[1])

        assertEquals(Resource.Status.SUCCESS, returnValue.status)
        assertEquals(resource, returnValue)
    }

    @Test
    fun `Test getRandomJoke(firstName, lastName) is unsuccessful with error message`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val charactersSplit = characterNameFromTextField.split(" ")
        val errorMsg = "Error when getting joke from api"
        val resource = Resource(Resource.Status.ERROR, null, errorMsg)

        //When
        `when`(mockDataSource.getRandomJoke(charactersSplit[0], charactersSplit[1]))
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke(charactersSplit[0], charactersSplit[1])


        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals(errorMsg, returnValue.message)
    }

    @Test
    fun `Test getRandomJoke(firstName, lastName) is unsuccessful with null error message`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val charactersSplit = characterNameFromTextField.split(" ")
        val resource = Resource(Resource.Status.ERROR, null, null)

        //When
        `when`(mockDataSource.getRandomJoke(charactersSplit[0], charactersSplit[1]))
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke(charactersSplit[0], charactersSplit[1])


        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals("Error Message Null", returnValue.message)
    }

    @Test
    fun `Test getRandomJoke(firstName, lastName) is unsuccessful with unknown status`() = runTest {
        //Given
        val characterNameFromTextField = "Joe Bloggs"
        val charactersSplit = characterNameFromTextField.split(" ")
        val resource = Resource(Resource.Status.LOADING, null, null)

        //When
        `when`(mockDataSource.getRandomJoke(charactersSplit[0], charactersSplit[1]))
            .thenReturn(resource)

        val returnValue = repository.getRandomJoke(charactersSplit[0], charactersSplit[1])

        assertEquals(Resource.Status.ERROR, returnValue.status)
        assertEquals("Unknown Status in JokesRepository", returnValue.message)
    }
}

