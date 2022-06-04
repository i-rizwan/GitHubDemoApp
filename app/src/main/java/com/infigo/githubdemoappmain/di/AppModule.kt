package com.infigo.githubdemoappmain.di

import android.content.Context
import androidx.room.Room
import com.infigo.githubdemoappmain.database.RegisterDatabase
import com.infigo.githubdemoappmain.database.RegisterDatabaseDao
import com.infigo.githubdemoappmain.database.RegisterRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): RegisterDatabase =
        Room.databaseBuilder(context, RegisterDatabase::class.java, "RegisterDataBase")
            .build()

    @Provides
    fun providesPostDao(registerDatabase: RegisterDatabase): RegisterDatabaseDao =
        registerDatabase.getRegisterData()

    @Provides
    fun providesPostRepository(registerDatabaseDao: RegisterDatabaseDao): RegisterRepository =
        RegisterRepository(registerDatabaseDao)

}