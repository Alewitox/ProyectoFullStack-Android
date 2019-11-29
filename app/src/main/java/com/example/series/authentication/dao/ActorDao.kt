package com.example.series.authentication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.series.authentication.model.Actor
import com.example.series.authentication.model.User

@Dao
interface ActorDao {
    @Insert
    fun insert(actor: Actor)

    @Update
    fun update(vararg actor: Actor)

    @Delete
    fun delete(vararg actor: Actor)


    @Query("SELECT * FROM "+ Actor.TABLE_NAME)
    fun getActors(): LiveData<List<Actor>>
}