package com.example.rickandmorty.presentation.character

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.presentation.general.MvpErrorView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharacterView : MvpErrorView, MvpView {
    fun showCharacter(character: Character)
}