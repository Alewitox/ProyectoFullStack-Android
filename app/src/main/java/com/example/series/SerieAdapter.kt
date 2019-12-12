package com.example.series

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class SeriesAdapter(

    private val mContext: Context,
    private val mData: List<Series>
) :
    RecyclerView.Adapter<SeriesAdapter.MyViewHolder>() {

    var option: RequestOptions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.series_home, parent, false)
        val viewHolder = MyViewHolder(view)



        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = mData[position].name
        holder.tvRating.text = mData[position].rating
        holder.tvStudio.text = mData[position].studio
        holder.tvCategory.text = mData[position].categorie

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData[position].image_url).apply(option)
            .into(holder.imgThumbnail)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvRating: TextView
        var tvStudio: TextView
        var tvCategory: TextView
        var imgThumbnail: ImageView

        init {
            tvName = itemView.findViewById(R.id.serieName)
            tvCategory = itemView.findViewById(R.id.categorie)
            tvRating = itemView.findViewById(R.id.rating)
            tvStudio = itemView.findViewById(R.id.studio)
            imgThumbnail = itemView.findViewById(R.id.thumbnail)
        }
    }

    init {
        // Request option for Glide
        option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
            .error(R.drawable.loading_shape)
    }
}


