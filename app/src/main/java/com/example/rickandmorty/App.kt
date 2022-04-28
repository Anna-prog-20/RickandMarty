package com.example.rickandmorty

import com.example.rickandmorty.di.DaggerCharactersComponent
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerCharactersComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .build()
}