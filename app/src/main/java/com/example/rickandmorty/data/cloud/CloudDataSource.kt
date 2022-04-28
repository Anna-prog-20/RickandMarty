package com.example.rickandmorty.data.cloud

import com.example.rickandmorty.data.api.CharacterApiGet
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Characters
import io.reactivex.Single
import javax.inject.Inject

class CloudDataSource @Inject constructor(
    private val characterApiGet: CharacterApiGet
) : DataSource {
    override fun getCharacters(): Single<List<Character>> =
        characterApiGet.getCharacters().map { it.characters }

    override fun getCharacters(pageNum: Int): Single<List<Character>> =
        characterApiGet.getCharacters(pageNum)
            .map { it.characters }

    override fun getInfoCharacters(): Single<Characters> =
        characterApiGet.getCharacters()

    override fun getInfoCharacters(pageNum: Int): Single<Characters> =
        characterApiGet.getCharacters(pageNum)

    override fun getCharacterById(idCharacter: String): Single<Character> =
        characterApiGet.getCharacter(idCharacter)

}