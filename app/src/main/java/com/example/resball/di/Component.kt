package com.example.resball.di

import com.example.resball.view.MainActivity
import com.example.resball.view.onboarding.screens.CountriesFragment
import dagger.Component

//class Component {
//}

@Component(
    modules = [
        NetworkModule::class,
        RoomModule::class
    ]
)
interface Component {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: CountriesFragment)
}