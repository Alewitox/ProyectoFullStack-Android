package com.example.series.authentication.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.series.authentication.dao.UserDao
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.User

class UsersRepository(application: Application) {
    private val userDao: UserDao? = TvShowDatabase.getInstance(application)?.userDao()

    fun insert(user:User) {
        if (userDao != null)
            InsertAsyncTask(userDao).execute(user)
    }

    fun updateToken(user: User) {
        if (userDao != null)
            UpdateTokenAsyncTask(userDao).execute(user)
    }

    fun getUsers(): LiveData<List<User>>? {

        if(userDao !=null) {
            Log.println(Log.INFO, null, "ALL USER")
            return userDao.getUsers()
        }
        Log.println(Log.INFO, null, "ALL USER NULL")
        return null
    }
    fun deleteUsers(user: User) {
        if (userDao != null)
            DeleteAsyncTask(userDao).execute(user).get()
        Log.println(Log.INFO, null, "BORRADO")

    }

    fun getUser(user_email:String):String {

        if (userDao != null)
            return EmailUserAsyncTask(userDao).execute(user_email).get()
        return ""
    }

    fun getToken(user_email:String):String {

        if (userDao != null)
            return SelectAsyncTask(userDao).execute(user_email).get()
        return ""
    }

    private class InsertAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user?.email != null && user?.token != null) {
                    userDao.insert(user)
                }

            }
            return null
        }
    }

    private class DeleteAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user?.email != null && user?.token != null) {
                    userDao.delete(user)
                }

            }
            return null
        }
    }

    private class SelectAsyncTask(private val userDao: UserDao) :
        AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg users_email: String?): String {
            for (user_email in users_email) {
                if (user_email != null)
                    return userDao?.getToken(user_email) ?:String.toString()
            }
            return ""
        }
    }

    private class EmailUserAsyncTask(private val userDao: UserDao) :
        AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg users_email: String?): String {
            for (user_email in users_email) {
                if (user_email != null)
                    return userDao?.getUser(user_email) ?:String.toString()
            }
            return ""
        }
    }

    private class UpdateTokenAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user?.email != null && user?.token != null)
                    userDao.updateToken(user!!.email,user!!.token)
            }
            return null
        }
    }
}