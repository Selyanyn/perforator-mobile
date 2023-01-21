package com.example.perforatormobile.di

class SessionManager {
    companion object {
        private var userTokenB = ""
        private var userTokenF = ""
    }

    fun saveAuthTokensBF(tokenB: String, tokenF: String) {
        userTokenB = tokenB
        userTokenF = tokenF
    }

    fun fetchAuthTokenB(): String {
        return userTokenB
    }

    fun fetchAuthTokenF(): String {
        return userTokenF
    }
}