package com.lloyd.common
import java.io.IOException

object ErrorFactory {

    fun getErrorMessage(e: Exception): String {
        return when (e) {
            is IOException -> "Couldn't reach server. Check your internet connection."
            else -> "An unexpected error occurred: ${e.localizedMessage}"
        }
    }
}