package com.example.foody.di

import android.content.Context
import androidx.room.Room
import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.RecipesDatabase
import com.example.foody.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) : RecipesDatabase = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(database: RecipesDatabase): RecipesDao = database.recipesDao()
}