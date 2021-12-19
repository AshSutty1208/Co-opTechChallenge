package com.sutton.joke_api.data.models

data class MultipleJokes(val type: String,
                         val value: ArrayList<Joke>)