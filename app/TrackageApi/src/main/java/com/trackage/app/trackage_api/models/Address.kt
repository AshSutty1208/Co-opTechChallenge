package com.trackage.app.trackage_api.models

data class Address(val id: Int,
                    val primary: Boolean,
                    val name: String,
                    val addressLine1: String,
                    val addressLine2: String,
                    val postcode: String)