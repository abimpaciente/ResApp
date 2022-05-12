package com.example.resball

import android.app.Application
import com.example.resball.di.Component
import com.example.resball.di.DaggerComponent
import dagger.internal.DaggerGenerated

class CoreApplication : Application() {
    lateinit var stringValue: String

    override fun onCreate() {
        super.onCreate()

        component = DaggerComponent.builder()
            .apply {
                val asd = this
            }
            .build()

        /*DaggerNycComponent.builder()
        .applicationModule(
            ApplicationModule(this)
        )
        .build()
*/
    }

    companion object {
        lateinit var component: Component
    }
}