package com.example.series.authentication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.series.authentication.model.Episode
import com.example.series.authentication.model.User

@Dao
interface EpisodeDao {
    @Insert
    fun insert(episode: Episode)

    @Update
    fun update(vararg episode: Episode)

    @Delete
    fun delete(vararg episode: Episode)


    @Query("SELECT * FROM "+ Episode.TABLE_NAME)
    fun getEpisodes(): LiveData<List<Episode>>
}