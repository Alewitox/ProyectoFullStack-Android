package com.example.series.authentication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.series.authentication.model.Serie
import com.example.series.authentication.repository.SerieRepository


class SerieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        SerieRepository(application)
    val series = repository.getSeries()

    fun saveSerie(serie: Serie) {
        repository.insert(serie)
    }

}