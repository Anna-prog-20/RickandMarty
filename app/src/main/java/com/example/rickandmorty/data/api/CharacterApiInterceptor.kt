package com.example.rickandmorty.data.api

import okhttp3.Interceptor
import okhttp3.Response

object CharacterApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .build()
        )

}