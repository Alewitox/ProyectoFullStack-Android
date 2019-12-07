package com.example.series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val JSON_URL =
        "https://gist.githubusercontent.com/Alewitox/452c2dc7112ba33313cf32cda8efd9b2/raw/03e6cf772f07c5db8cb683cd40bab8caf0b731af/animelist"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstSerie: MutableList<Series>? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        lstSerie = ArrayList()
        recyclerView = findViewById(R.id.recyclerViewSeries)
        jsonrequest()
    }

    fun jsonrequest() {
        request =
            JsonArrayRequest(JSON_URL,
                Response.Listener { response ->
                    var jsonObject: JSONObject?
                    for (i in 0 until response.length()) {
                        try {
                            jsonObject = response.getJSONObject(i)
                            val serie = Series()
                            serie.name = jsonObject.getString("name")
                            serie.rating = jsonObject.getString("Rating")
                            serie.image_url = jsonObject.getString("img")
                            serie.studio = jsonObject.getString("studio")
                            serie.categorie = jsonObject.getString("categorie")
                            lstSerie!!.add(serie)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    setuprecyclerview(lstSerie)
                }, Response.ErrorListener { })
        requestQueue = Volley.newRequestQueue(this@HomeActivity)
        requestQueue!!.add(request)
    }

    fun setuprecyclerview(lstAnime: List<Series>?) {
        val myadapter = SeriesAdapter(this, lstSerie!!)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = myadapter
    }
}
