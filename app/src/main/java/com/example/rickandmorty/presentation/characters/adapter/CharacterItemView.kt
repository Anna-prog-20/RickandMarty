package com.example.rickandmorty.presentation.characters.adapter

import com.example.rickandmorty.data.model.Character

interface CharacterItemView {
    fun bind(category: Character, delegate: CharactersAdapter.Delegate)
}