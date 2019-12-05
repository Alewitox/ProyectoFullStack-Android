package com.example.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(var actors: ArrayList<Actors>) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_actors, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.actorName.text = actors[position].ActorName
        holder.characterName.text = actors[position].CharacterName





    }

    override fun getItemCount(): Int {
        return actors.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var actorName: TextView
        var characterName: TextView




        init {
            actorName = v.findViewById(R.id.actornameid)
            characterName = v.findViewById(R.id.characternameid)



        }
    }

}