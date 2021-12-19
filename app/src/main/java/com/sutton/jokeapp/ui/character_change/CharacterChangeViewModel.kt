package com.sutton.jokeapp.ui.character_change

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sutton.joke_api.data.repository.JokesRepository
import com.sutton.joke_api.utils.Resource
import com.sutton.jokeapp.util.DispatcherProvider
import com.sutton.jokeapp.util.containsOnlyLetters
import com.sutton.jokeapp.util.splitBySpace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterChangeViewModel @Inject constructor(private val jokesRepository: JokesRepository,
                                                   private val dispatcher: DispatcherProvider) : ViewModel() {
    var characterTextValue = MutableLiveData("")
        private set

    var dialogState = MutableLiveData(false)
        private set

    var dialogText = MutableLiveData("")
        private set

    val characterTextInputValid = MediatorLiveData<Boolean>().apply {
        //Add CharacterTextValue as a source of this mediator
        addSource(characterTextValue) {
            value = isTextFieldValid(it)
        }
    }

    /**
     * Fetches a random joke with a given string, the string will be split into a first and last
     * name so it can be sent to the api for a name change.
     *
     * Returns from the api a joke with the character name changed from Chuck Norris.
     */
    fun onSearchButtonClicked(textInputString: String?) {
        val textInputSplit = textInputString?.splitBySpace() ?: emptyList()

        viewModelScope.launch {
            withContext(dispatcher.io()) {
                val response = jokesRepository.getRandomJoke(textInputSplit[0], textInputSplit[1])

                withContext(dispatcher.main()) {
                    when (response.status) {
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

    /**
     * Checks if the Character text field is valid.
     *
     * @param textInputString - String value from the TextField in CharacterChangeActivity
     * @return Boolean - Returns true if text is valid
     */
    private fun isTextFieldValid(textInputString: String): Boolean {
        //Check if the text only contains letters
        return if (textInputString.containsOnlyLetters()) {
            // Check if there is more than a first and last name or if there is only one name
            val textInputSplit = textInputString.splitBySpace()
            if (textInputSplit.size > 2 || textInputSplit.size == 1) {
                return false
            // Check if either the first name or the last name is empty
            } else if (textInputSplit[0].isEmpty() || textInputSplit[1].isEmpty()) {
                return false
            }

            true
        } else {
            false
        }
    }
}