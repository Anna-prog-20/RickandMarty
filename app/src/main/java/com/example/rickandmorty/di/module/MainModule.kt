package com.example.rickandmorty.di.module

import com.example.rickandmorty.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}