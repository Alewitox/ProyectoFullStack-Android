package com.example.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SeasonsAdapter(var seasons: ArrayList<Seasons>) :
    RecyclerView.Adapter<SeasonsAdapter.ViewHolder>() {
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
        holder.seasonNumber.text = seasons[position].SeasonNumber
        holder.episodeNumber.text = seasons[position].EpisodeNumber
        holder.episodeDate.text = seasons[position].EpisodeDate
        holder.episodeTitle.text = seasons[position].EpisodeTitle
        holder.episodeRating.text = seasons[position].EpisodeRating




    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var seasonNumber: TextView
        var episodeNumber: TextView
        var episodeDate: TextView
        var episodeTitle: TextView
        var episodeRating: TextView



        init {
            seasonNumber = v.findViewById(R.id.seasonnumberid)
            episodeNumber = v.findViewById(R.id.episodenumberid)
            episodeDate = v.findViewById(R.id.episodedateid)
            episodeTitle = v.findViewById(R.id.episodetitleid)
            episodeRating = v.findViewById(R.id.episoderatingid)


        }
    }

}
