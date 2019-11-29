package com.example.series.authentication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.series.authentication.model.Serie
import com.example.series.authentication.model.User

@Dao
interface SerieDao {
    @Insert
    fun insert(serie: Serie)

    @Update
    fun update(vararg serie: Serie)

    @Delete
    fun delete(vararg serie: Serie)


    @Query("SELECT * FROM "+ Serie.TABLE_NAME)
    fun getSeries(): LiveData<List<Serie>>
}