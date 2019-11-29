package com.example.series.authentication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.series.authentication.model.Actor
import com.example.series.authentication.repository.ActorRepository


class ActorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        ActorRepository(application)
    val actor = repository.getActors()

    fun saveActor(actor: Actor) {
        repository.insert(actor)
    }


}