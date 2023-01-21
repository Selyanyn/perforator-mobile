package com.example.perforatormobile.di

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private val sessionManager = SessionManager()

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthTokenF().let {
            if (it != "") {
                requestBuilder.addHeader("token", it)
            }
            requestBuilder.addHeader("isMobile", "True")
        }

        return chain.proceed(requestBuilder.build())
    }
}