package com.example.series.authentication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.series.authentication.dao.ActorDao
import com.example.series.authentication.dao.EpisodeDao
import com.example.series.authentication.dao.SerieDao
import com.example.series.authentication.model.User
import com.example.series.authentication.dao.UserDao
import com.example.series.authentication.model.Actor
import com.example.series.authentication.model.Episode
import com.example.series.authentication.model.Serie

@Database(entities = [User::class, Serie::class, Episode::class, Actor::class], version = 1)
abstract class TvShowDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun serieDao(): SerieDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun actorDao(): ActorDao

    companion object {
        private const val DATABASE_NAME = "tv_series"
        @Volatile
        private var INSTANCE: TvShowDatabase? = null

        fun getInstance(context: Context): TvShowDatabase? {
            INSTANCE
                ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TvShowDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}