package com.example.resball.di

import com.example.resball.rest.Repository
import com.example.resball.rest.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepoLayer(repositoryImpl: RepositoryImpl): Repository
}