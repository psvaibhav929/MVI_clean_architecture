package com.mvi.data.network

import com.mvi.domain.result.ApiResult
import retrofit2.Response

object SafeApiCall {
    suspend fun <T : Any, R : Any> call(
        apiCall: suspend () -> Response<T>,
        mapper: (T) -> R
    ): ApiResult<R> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ApiResult.Success(mapper(body))
                } else {
                    ApiResult.Error(Throwable("Response body is null"))
                }
            } else {
                ApiResult.Error(Throwable(ErrorFactory.getErrorMessageFromCode(response.code())))
            }
        } catch (e: Exception) {
            ApiResult.Error(Throwable(ErrorFactory.getErrorMessage(e)))
        }
    }
}