package com.example.resball.di

import com.example.resball.view.onboarding.screens.CountriesFragment
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface Component {
    //    fun inject(mainActivity: MainActivity)
    fun inject(fragment: CountriesFragment)
}