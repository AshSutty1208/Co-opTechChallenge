package com.trackage.app.ui.deliveries

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DeliveriesViewModel @Inject constructor(private val trackageRepository: TrackageRepository,
                                              private val dispatcher: DispatcherProvider
) : ViewModel() {
    var uiState by mutableStateOf(DeliveriesState())
        private set

    fun populateData() {
        viewModelScope.launch {
            withContext(dispatcher.io()) {
                // Artificial loading to fake api
                val deliveriesApi = trackageRepository.getUserDeliveries()

                withContext(dispatcher.main()) {
                    if (deliveriesApi != null) {
                        uiState = DeliveriesState(deliveriesApi.deliveries, deliveriesApi.customerId)
                    }
                }
            }
        }
    }
}