package com.example.series.authentication.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.series.authentication.dao.ActorDao
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.Actor


class ActorRepository(application: Application) {
    private val actorDao: ActorDao? = TvShowDatabase.getInstance(application)?.actorDao()

    fun insert(actor: Actor) {
        if (actorDao != null) InsertAsyncTask(actorDao).execute(actor)
    }

    fun getActors(): LiveData<List<Actor>> {
        return actorDao?.getActors() ?: MutableLiveData<List<Actor>>()
    }


    private class InsertAsyncTask(private val actorDao: ActorDao) :
        AsyncTask<Actor, Void, Void>() {
        override fun doInBackground(vararg actors: Actor?): Void? {
            for (actor in actors) {
                if (actor != null) actorDao.insert(actor)
            }
            return null
        }
    }


}