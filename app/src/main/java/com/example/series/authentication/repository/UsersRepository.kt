package com.example.series.authentication.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.series.authentication.dao.UserDao
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.User

class UsersRepository(application: Application) {
    private val userDao: UserDao? = TvShowDatabase.getInstance(application)?.userDao()

    fun insert(user: User) {
        if (userDao != null) InsertAsyncTask(userDao).execute(user)
    }

    fun getUsers(): LiveData<List<User>> {
        return userDao?.getUsers() ?: MutableLiveData<List<User>>()
    }

    fun getUser(user_email:String):String {

        if (userDao != null)
            return SelectAsyncTask(userDao!!).execute(user_email).get()
        return ""
    }

    private class InsertAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user != null) userDao.insert(user)
            }
            return null
        }
    }

    private class SelectAsyncTask(private val userDao: UserDao) :
        AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg users_email: String?): String? {
            for (user_email in users_email) {
                if (user_email != null)
                    return userDao?.getUser(user_email) ?:String.toString()
            }
            return null
        }
    }
}