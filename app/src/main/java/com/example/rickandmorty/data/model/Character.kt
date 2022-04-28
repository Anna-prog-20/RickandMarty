package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("results")
    val characters: List<Character>,
    @SerializedName("info")
    val infoCharacters: CharacterPage
)

data class CharacterPage(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String?
) {
    fun nextPage() = next.replace("https://rickandmortyapi.com/api/character?page=", "").toInt()
    fun prevPage() = prev?.let {
        it.replace("https://rickandmortyapi.com/api/character?page=", "").toInt()
    }
}

data class Character(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val avatarURL: String,
    @SerializedName("episode")
    val episode: MutableList<String>,
    @SerializedName("location")
    val location: Location
) {
    fun countEpisodes(): Int = episode.size
    fun countEpisodesToString() = "${countEpisodes()}"
}

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)