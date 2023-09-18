package com.example.horoscopeapp.di

import android.app.Application
import androidx.room.Room
import com.example.horoscopeapp.data.database.SavedAgeDatabase
import com.example.horoscopeapp.data.network.AgeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

//Contains all dependencies for App module. It is a singleton.
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android){
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
            install(Logging){
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun provideAgeService(client: HttpClient): AgeService {
        return AgeService(client)
    }

    @Provides
    @Singleton
    fun provideSavedArticlesDatabase(app: Application): SavedAgeDatabase {
        return Room.databaseBuilder(
            app,
            SavedAgeDatabase::class.java,
            "saved_age.db"
        ).build()
    }
}