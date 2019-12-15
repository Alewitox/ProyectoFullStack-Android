package com.example.series


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.series.adapters.SeriesAdapter
import com.example.series.models.Series
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class HomeActivity : AppCompatActivity() {

    private val JSON_URL = "http://192.168.1.210:8000/api/all/series"
    private var request: JsonArrayRequest? = null
    private var requestQueue: RequestQueue? = null
    private var lstSerie: MutableList<Series>? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)


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
        val myadapter =
            SeriesAdapter(this, lstSerie!!)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = myadapter
    }




    override fun onPrepareOptionsMenu(menu : Menu):Boolean {
        //Se accede al ítem usando el id que
        //tiene dentro del menú directamente
        var opcion1 = menu.findItem(R.id.menuProfile)
        opcion1.setEnabled(true)

        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menuProfile) {
            val b = Intent(this, ProfileActivity::class.java)
            startActivity(b)
            return true
        } else if (id == R.id.menuProfile) {
            val b = Intent(this, ProfileActivity::class.java)
            startActivity(b)
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
