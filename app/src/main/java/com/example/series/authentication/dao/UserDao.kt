package com.example.series.authentication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.series.authentication.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(vararg user: User)

    @Delete
    fun delete(vararg user: User)

    @Query("UPDATE "+ User.TABLE_NAME +" SET token=:token WHERE email = :email")
    fun updateToken(email:String,token:String)

    @Query("SELECT token FROM " + User.TABLE_NAME + " WHERE email=:user_email")
    fun getUser(user_email:String):String

    @Query("SELECT * FROM "+ User.TABLE_NAME)
    fun getUsers(): LiveData<List<User>>
}