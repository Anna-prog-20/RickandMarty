package com.example.rickandmorty.di

import com.example.rickandmorty.presentation.character.CharacterFragment
import com.example.rickandmorty.presentation.characters.CharactersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CharacterUiModule {
    @ContributesAndroidInjector
    abstract fun bindCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector
    abstract fun bindCharacterFragment(): CharacterFragment
}