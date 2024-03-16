package com.mvi.data.network

import com.google.gson.JsonParseException
import java.io.IOException

object ErrorFactory {

    fun getErrorMessage(e: Exception): String {
        return when (e) {
            is IOException -> "Couldn't reach server. Check your internet connection."
            is JsonParseException -> "JSON parsing error: ${e.message}"
            else -> "An unexpected error occurred: ${e.localizedMessage}"
        }
    }

    fun getErrorMessageFromCode(code: Int): String {
        return when (code) {
            500 -> "Oops! Something went wrong on our end. Our team has been notified, and we're working to fix it as soon as possible."
            501 -> "We're sorry, but this feature isn't available right now. We're working to add it soon!"
            502 -> "Uh-oh! It seems there's a hiccup connecting to our servers. Please try again in a few moments."
            503 -> "Our servers are currently overloaded or undergoing maintenance. "
            504 -> "It looks like our servers are taking too long to respond. Please check your internet connection and try again."
            505 -> "We're sorry, but the version of the website you're trying to access isn't supported. Please make sure you're using the latest version of your browser."
            else -> {
                "Something went wrong"
            }
        }
    }
}