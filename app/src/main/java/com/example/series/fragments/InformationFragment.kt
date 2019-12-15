package com.example.series.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.example.series.R
import com.example.series.VolleyRequestUser

/**
 * A simple [Fragment] subclass.
 */
class InformationFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_information, container, false)

        var description = root.findViewById<TextView>(R.id.descriptionid)
        var originalTitle = root.findViewById<TextView>(R.id.originalTitleid)
        var originalLanguage = root.findViewById<TextView>(R.id.originalLanguageid)
        var firstEpisode = root.findViewById<TextView>(R.id.firstEpisodeDate)
        var lastEpisode = root.findViewById<TextView>(R.id.lastEpisodeDate)
        var status = root.findViewById<TextView>(R.id.serieStatus)
        var network = root.findViewById<TextView>(R.id.networkid)
        var creator = root.findViewById<TextView>(R.id.creatorsid)

        requestToServer(inflater.context, description, originalTitle, originalLanguage, firstEpisode, lastEpisode, status, network, creator)
        return root
    }

}


fun requestToServer(context: Context, descriptionid: TextView, originalTitleid: TextView, originalLanguageid: TextView, firstEpisodeDate: TextView, lastEpisodeDate: TextView, serieStatus: TextView, networkid: TextView, creatorsid: TextView) {


    val URL = "http://192.168.1.210:8000"

    val queue = Volley.newRequestQueue(context)
    val url = VolleyRequestUser.URL + "/api/all/series/1"
    val req = object : JsonObjectRequest(
        Request.Method.GET, url, null,
        Response.Listener {

                descriptionid.setText(it.getString("description"))
                originalTitleid.setText(it.getString("original_title"))
                originalLanguageid.setText(it.getString("original_language"))
                serieStatus.setText(it.getString("status"))
                firstEpisodeDate.setText(it.getString("first_air_date"))
                lastEpisodeDate.setText(it.getString("last_air_date"))
                creatorsid.setText(it.getString("creator"))
                networkid.setText(it.getString("network"))
            //}

        },
        Response.ErrorListener {

        })
    {}

    queue.add(req)
}