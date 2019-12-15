package com.example.series.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.series.R
import com.example.series.models.Seasons

class SeasonAdapter(var seasons: ArrayList<Seasons>) :
    RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_seasons, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.episodeNumber.text = seasons[position].EpisodeNumber
        holder.episodeDate.text = seasons[position].EpisodeDate
        holder.episodeTitle.text = seasons[position].EpisodeTitle
        holder.episodeRating.text = seasons[position].EpisodeRating
        holder.episodeDescription.text = seasons[position].EpisodeDescription




    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var episodeNumber: TextView
        var episodeDate: TextView
        var episodeTitle: TextView
        var episodeRating: TextView
        var episodeDescription: TextView



        init {
            episodeNumber = v.findViewById(R.id.episodenumberid)
            episodeDate = v.findViewById(R.id.episodedateid)
            episodeTitle = v.findViewById(R.id.episodetitleid)
            episodeRating = v.findViewById(R.id.episoderatingid)
            episodeDescription = v.findViewById(R.id.episodedescriptionid)


        }
    }

}
