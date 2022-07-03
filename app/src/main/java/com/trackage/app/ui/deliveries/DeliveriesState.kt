package com.trackage.app.ui.deliveries

import com.trackage.app.trackage_api.models.Delivery

data class DeliveriesState(
    val deliveriesToDisplay: List<Delivery> = emptyList(),
    val deliveriesCustomerId: Int = -1
)