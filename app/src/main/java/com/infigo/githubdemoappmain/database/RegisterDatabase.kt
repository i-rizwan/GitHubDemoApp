package com.infigo.githubdemoappmain.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun getRegisterData(): RegisterDatabaseDao

}

