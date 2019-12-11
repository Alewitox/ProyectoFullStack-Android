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


class ActorAdapter(private val mContext: Context, private val mData: List<Actors>): RecyclerView.Adapter<ActorAdapter.MyViewHolder>() {

    var option: RequestOptions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(mContext)
        view = inflater.inflate(R.layout.fragment_actors, parent, false)
        val viewHolder = MyViewHolder(view)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.characterName.text = mData[position].character_name
        holder.actorName.text = mData[position].actor_name

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData[position].image_url_actor).apply(option)
            .into(holder.imgThumbnailActor)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var characterName: TextView
        var actorName: TextView
        var imgThumbnailActor: ImageView

        init {
            characterName = itemView.findViewById(R.id.characternameid)
            actorName = itemView.findViewById(R.id.actornameid)
            imgThumbnailActor = itemView.findViewById(R.id.thumbnailActor)
        }
    }

    init {
        // Request option for Glide
        option = RequestOptions().centerCrop().placeholder(R.drawable.loading_shape)
            .error(R.drawable.loading_shape)
    }
}


