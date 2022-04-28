package com.example.rickandmorty.data.cloud

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.data.model.Location
import io.reactivex.Single
import retrofit2.http.Query

interface DataSource {
    fun getCharacters(): Single<List<Character>>
    fun getInfoCharacters(): Single<Characters>
    fun getInfoCharacters(pageNum: Int): Single<Characters>
    fun getCharacterById(idCharacter: String): Single<Character>
    fun getCharacters(pageNum: Int): Single<List<Character>>
}