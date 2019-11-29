package com.example.series.authentication.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.series.authentication.dao.SerieDao
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.Serie


class SerieRepository(application: Application) {
    private val serieDao: SerieDao? = TvShowDatabase.getInstance(application)?.serieDao()

    fun insert(serie: Serie) {
        if (serieDao != null) InsertAsyncTask(serieDao).execute(serie)
    }

    fun getSeries(): LiveData<List<Serie>> {
        return serieDao?.getSeries() ?: MutableLiveData<List<Serie>>()
    }


    private class InsertAsyncTask(private val serieDao: SerieDao) :
        AsyncTask<Serie, Void, Void>() {
        override fun doInBackground(vararg series: Serie?): Void? {
            for (serie in series) {
                if (serie != null) serieDao.insert(serie)
            }
            return null
        }
    }


}