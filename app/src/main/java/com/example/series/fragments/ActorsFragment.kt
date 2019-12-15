package com.example.series.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.series.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ActorsFragment : Fragment() {

    private val JSON_URL =
        "http://192.168.1.210:8000/api/all/actors"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstActor: MutableList<Actors>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.activity_actors, container, false)

        lstActor = ArrayList()
        recyclerView = root.findViewById(R.id.recyclerViewActors)

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
                            val actor = Actors()
                            actor.character_name = jsonObject.getString("character_name")
                            actor.actor_name = jsonObject.getString("actor_name")
                            actor.image_url_actor = jsonObject.getString("img")
                            lstActor!!.add(actor)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    setuprecyclerview(context, lstActor)
                }, Response.ErrorListener { })
        requestQueue = Volley.newRequestQueue(context)
        requestQueue!!.add(request)
    }

    fun setuprecyclerview(context: Context, lstActor: List<Actors>?) {
        val myadapter = ActorAdapter(context, lstActor!!)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = myadapter
    }


}
