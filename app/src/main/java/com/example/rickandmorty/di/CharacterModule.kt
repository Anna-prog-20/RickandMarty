package com.example.rickandmorty.di

import com.example.rickandmorty.data.cloud.CloudDataSource
import com.example.rickandmorty.data.cloud.DataSource
import com.example.rickandmorty.di.module.NetworkModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [CharacterUiModule::class, NetworkModule::class])
interface CharacterModule {
    @Singleton
    @Binds
    fun bindCloudDataSource(cloudDataSource: CloudDataSource): DataSource
}