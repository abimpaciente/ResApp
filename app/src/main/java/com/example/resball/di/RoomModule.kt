package com.example.resball.di

import android.content.Context
import androidx.room.Room
import com.example.resball.data.database.SportsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val SPORTS_DATABASE_NAME = "sport_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SportsDatabase::class.java, SPORTS_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCountriesDao(db: SportsDatabase) = db.getCountriesDao()
}