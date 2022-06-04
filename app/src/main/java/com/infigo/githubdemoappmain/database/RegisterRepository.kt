package com.infigo.githubdemoappmain.database

import android.util.Log
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val databaseDao: RegisterDatabaseDao) {

    val users = databaseDao.getAllUsers()
    suspend fun insert(user: RegisterEntity) {
        return databaseDao.insert(user)
    }

    suspend fun getUserName(userName: String): RegisterEntity? {
        Log.i("TAG", "inside Repository get users fun ")
        return databaseDao.getUsername(userName)
    }

}