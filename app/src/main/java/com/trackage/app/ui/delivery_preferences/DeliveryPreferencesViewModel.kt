package com.trackage.app.ui.delivery_preferences

import android.content.res.AssetManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.trackage_api.utils.Resource
import com.trackage.app.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class DeliveryPreferencesViewModel @Inject constructor(private val trackageRepository: TrackageRepository,
                                                       private val dispatcher: DispatcherProvider
) : ViewModel() {
    var primaryAddress = mutableStateOf("")
        private set

    var postCode = mutableStateOf("")
        private set

    fun populateData() {
        viewModelScope.launch {
            withContext(dispatcher.io()) {
                // Artificial loading to fake api
                val user = trackageRepository.getUserDetails()

                withContext(dispatcher.main()) {
                    if (user != null) {
                        primaryAddress.value = user.addresses[0].addressLine1
                        postCode.value = user.addresses[0].postcode
                    } else {
                        primaryAddress.value = "Failed to get data"
                        postCode.value = "Failed to get data"
                    }
                }
            }
        }
    }
}