package com.trackage.app.trackage_api.models

data class MultipleJokes(val type: String,
                         val value: ArrayList<Joke>)