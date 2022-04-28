package com.example.rickandmorty.presentation

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.presentation.character.CharacterFragment
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : Screens {
    override fun characters(): Screen =
        FragmentScreen { CharactersFragment.newInstance() }

    override fun character(character: Character): Screen =
        FragmentScreen { CharacterFragment.newInstance(character) }
}