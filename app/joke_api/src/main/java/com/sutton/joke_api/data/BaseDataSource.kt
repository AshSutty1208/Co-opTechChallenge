package com.sutton.joke_api.data

import com.sutton.joke_api.utils.Resource
import retrofit2.Response

/**
 * This is a pretty standard result generic.
 *
 * This is to prevent duplication of code when trying to call the api. Also the aid of having the
 * Resource util also allows us to encapsulate the response into a nice readable way when dealing
 * with it in the view.
 *
 * @param call - Lambda suspend function, this given function will be called and its response checked.
 * @returns Resource<T> - Returns a Resource of a type given by the Lambda function.
 */
abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for reason: $message")
    }
}