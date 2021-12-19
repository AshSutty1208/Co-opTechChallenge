package com.sutton.joke_api.utils

/**
 * This data class encapsulates the given Type and allows it to be used in a readable way.
 *
 * This class creates itself depending on the result from the api
 *
 * @param status - enum class which can be either SUCCESS, ERROR or LOADING
 * @param data - any data given back from the api for example a response
 * @param message - a message (Only used for errors)
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}