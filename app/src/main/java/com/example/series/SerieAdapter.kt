package com.example.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.series.authentication.model.Serie


class SeriesAdapter(var series: ArrayList<Series>) :
    RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.series_home, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.serieName.text = series[position].name
        holder.categorie.text = series[position].categorie
        holder.rating.text = series[position].rating
        holder.studio.text = series[position].studio



        
    }

    override fun getItemCount(): Int {
        return series.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var serieName: TextView
        var categorie: TextView
        var rating: TextView
        var studio: TextView



        init {
            serieName = v.findViewById(R.id.serieName)
            categorie = v.findViewById(R.id.categorie)
            rating = v.findViewById(R.id.rating)
            studio = v.findViewById(R.id.studio)


        }
    }

}
