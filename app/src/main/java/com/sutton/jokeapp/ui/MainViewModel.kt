package com.sutton.jokeapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sutton.joke_api.data.repository.JokesRepository
import com.sutton.joke_api.utils.Resource
import com.sutton.jokeapp.util.DefaultDispatcherProvider
import com.sutton.jokeapp.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val jokesRepository: JokesRepository,
                                        private val dispatcher: DispatcherProvider) : ViewModel() {
    var dialogState = MutableLiveData(false)
        private set

    var dialogText = MutableLiveData("")
        private set

    /**
     * Fetches a random joke from the api then sets the dialogState to true (To make it visible)
     * Also sets the dialogs text to the random joke
     *
     * There is 100% a better way of doing this but I am not sure what it is
     *
     * Both of these sets make the views recompose
     */
    fun fetchRandomJoke() {
        viewModelScope.launch {
            withContext(dispatcher.io()) {
                val response = jokesRepository.getRandomJoke()

                withContext(dispatcher.main()) {
                    when(response.status) {
                        Resource.Status.SUCCESS -> {
                            dialogState.value = true
                            dialogText.value = response.data?.value?.joke
                                ?: "THERE WAS AN ERROR GETTING A RANDOM JOKE"
                        }
                        Resource.Status.ERROR -> {
                            dialogState.value = true
                            dialogText.value = response.message
                                ?: "THERE WAS AN ERROR GETTING A RANDOM JOKE"
                        }
                        Resource.Status.LOADING -> {
                            //Do nothing
                        }
                    }
                }
            }
        }
    }
}