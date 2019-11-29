package com.example.series.authentication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.series.authentication.model.Episode
import com.example.series.authentication.repository.EpisodeRepository

class EpisodeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        EpisodeRepository(application)
    val episodes = repository.getEpisodes()

    fun saveEpisode(episode: Episode) {
        repository.insert(episode)
    }

}