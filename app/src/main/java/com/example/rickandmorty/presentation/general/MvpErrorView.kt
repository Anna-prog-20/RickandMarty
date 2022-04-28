package com.example.rickandmorty.presentation.general

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface MvpErrorView : MvpView {

    @SingleState
    fun showError(message: String?)

}