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
        "http://192.168.103.210:8000/api/all/series"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstSerie: MutableList<Series>? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /*linearSeries.setOnClickListener{
            var intent = Intent(this,TabLayout::class.java)
            startActivity(intent)
        }*/

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
                            serie.name = jsonObject.getString("title")
                            serie.rating = jsonObject.getString("rating")
                            serie.image_url = jsonObject.getString("img")
                            serie.studio = jsonObject.getString("network")
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

    fun setuprecyclerview(lstSerie: List<Series>?) {
        val myadapter = SeriesAdapter(this, lstSerie!!)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = myadapter
    }
}
