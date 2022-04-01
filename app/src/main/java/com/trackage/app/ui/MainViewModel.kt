package com.trackage.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.trackage_api.utils.Resource
import com.trackage.app.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val trackageRepository: TrackageRepository,
                                        private val dispatcher: DispatcherProvider
) : ViewModel() {
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
                val response = trackageRepository.getRandomJoke()

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