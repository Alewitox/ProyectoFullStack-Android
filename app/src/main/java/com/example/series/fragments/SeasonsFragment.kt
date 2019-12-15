package com.example.series.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.series.R
import com.example.series.adapters.SeasonAdapter
import com.example.series.models.Seasons
import org.json.JSONException
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class SeasonsFragment : Fragment() {

    private val JSON_URL = "http://192.168.1.210:8000/api/all/episodes"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstSeason: MutableList<Seasons>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.activity_seasons, container, false)

        lstSeason = ArrayList()
        recyclerView = root.findViewById(R.id.recyclerViewSeasons)

        jsonrequest(root.context)


        return root
    }

    fun jsonrequest(context: Context) {
        request =
            JsonArrayRequest(JSON_URL,
                Response.Listener { response ->
                    var jsonObject: JSONObject?
                    for (i in 0 until response.length()) {
                        try {
                            jsonObject = response.getJSONObject(i)
                            val season = Seasons(
                                "",
                                "",
                                "",
                                "",
                                ""
                            )
                            season.EpisodeNumber = jsonObject.getString("episode_number")
                            season.EpisodeDate = jsonObject.getString("release_date")
                            season.EpisodeTitle = jsonObject.getString("title")
                            season.EpisodeRating = jsonObject.getString("rating")
                            season.EpisodeDescription = jsonObject.getString("description")
                            lstSeason!!.add(season)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    setuprecyclerview(lstSeason as ArrayList<Seasons>)
                }, Response.ErrorListener { })
        requestQueue = Volley.newRequestQueue(context)
        requestQueue!!.add(request)
    }

    fun setuprecyclerview(lstSeason: ArrayList<Seasons>?) {
        val myadapter = SeasonAdapter(lstSeason!!)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = myadapter
    }


}
