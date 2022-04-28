package com.example.rickandmorty.di.module

import com.example.rickandmorty.data.api.CharacterApiGet
import com.example.rickandmorty.data.api.CharacterApiInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val gson: Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Singleton
    @Provides
    fun provideCharacterApi(): CharacterApiGet =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(CharacterApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CharacterApiGet::class.java)

}