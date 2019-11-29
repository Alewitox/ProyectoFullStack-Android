package com.example.series.authentication.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.series.authentication.dao.EpisodeDao
import com.example.series.authentication.dao.UserDao
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.Episode
import com.example.series.authentication.model.Serie
import com.example.series.authentication.model.User

class EpisodeRepository(application: Application) {
    private val episodeDao: EpisodeDao? = TvShowDatabase.getInstance(application)?.episodeDao()

    fun insert(episode: Episode) {
        if (episodeDao != null) InsertAsyncTask(episodeDao).execute(episode)
    }

    fun getEpisodes(): LiveData<List<Episode>> {
        return episodeDao?.getEpisodes() ?: MutableLiveData<List<Episode>>()
    }


    private class InsertAsyncTask(private val episodeDao: EpisodeDao) :
        AsyncTask<Episode, Void, Void>() {
        override fun doInBackground(vararg episodes: Episode?): Void? {
            for (episode in episodes) {
                if (episode != null) episodeDao.insert(episode)
            }
            return null
        }
    }


}