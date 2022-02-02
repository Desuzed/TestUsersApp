package com.desuzed.testusersapp.data

import android.content.res.Resources
import com.desuzed.testusersapp.R

class ErrorProviderImpl(private val resources: Resources) : ErrorProvider {
    override fun parseCode(code: Int): String {
        return when (code) {
            10 -> resources.getString(R.string.network_error)
            in 300..399 -> resources.getString(R.string.redirect_error)
            in 400..499 -> resources.getString(R.string.api_error)
            in 500..599 -> resources.getString(R.string.server_error)
            else -> resources.getString(R.string.unknown_error)
        }
    }
}

interface ErrorProvider {
    fun parseCode(code: Int): String

    companion object {
        const val NETWORK_ERROR = 10
    }
}

