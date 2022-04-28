package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Characters
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiGet {
    @GET("character")
    fun getCharacters(): Single<Characters>

    @GET("character/{idCharacter}")
    fun getCharacter(@Path("idCharacter") idCharacter: String): Single<Character>

    @GET("character")
    fun getCharacters(@Query("page") pageNum: Int): Single<Characters>
}