package com.mvi.data.network

import retrofit2.Response

object SafeApiCall {
    suspend fun <T : Any, R : Any> call(apiCall: suspend () -> Response<T>, mapper: (T) -> R): Result<R> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(mapper(body))
                } else {
                    Result.failure(Throwable("Response body is null"))
                }
            } else {
                Result.failure(Throwable(ErrorFactory.getErrorMessageFromCode(response.code())))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(ErrorFactory.getErrorMessage(e)))
        }
    }}