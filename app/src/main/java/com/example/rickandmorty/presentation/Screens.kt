package com.example.rickandmorty.presentation

import com.example.rickandmorty.data.model.Character
import com.github.terrakok.cicerone.Screen

interface Screens {
    fun characters(): Screen
    fun character(character: Character): Screen
}