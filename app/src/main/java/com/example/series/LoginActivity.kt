package com.example.series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.User
import com.example.series.authentication.viewModel.UsersViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private var db: TvShowDatabase? = null
    private lateinit var token:String

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        lg_loginText.setOnClickListener(){
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        lg_login.setOnClickListener(){
            login()
            //syncUpUsers()

        }
    }

    fun login() {

        // User input
        val loginJsonobj = JSONObject()

        loginJsonobj.put("email", lg_email.text)
        loginJsonobj.put("password", lg_password.text)

        // new Volley newRequestQueue
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.210:8000/api/auth/login"
        val req = object : JsonObjectRequest(
            Request.Method.POST, url, loginJsonobj,
            Response.Listener {
                token=it.getString("token")

                Observable.fromCallable {
                    db = TvShowDatabase.getInstance(context = this)
                    //db?.userDao()?.insert(User("admin", "admin@admin.com", "admin", ""))
                    db?.userDao()?.updateToken(lg_email.text.toString(),token)

                }.doOnNext({}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()

                showToken()
            },
            Response.ErrorListener {
                Toast.makeText(this, "Email o contraseña erróneo", Toast.LENGTH_SHORT).show()
            })
        {}

        queue.add(req)

    }

    private fun showToken() {

        var token:String= usersViewModel.getUser(lg_email.text.toString())
        Toast.makeText(this, "Token " + token, Toast.LENGTH_LONG).show()
    }


    /*fun syncUpUsers() {

        var array=JSONArray()

        Log.println(Log.INFO, null, "syncUpUsers ")

        // new Volley newRequestQueue
        val updateQueue = Volley.newRequestQueue(this)
        val updateUrl = "http://192.168.103.210:8000/api/auth/users"
        val updateReq = object : JsonArrayRequest(
            Request.Method.GET, updateUrl, null,
            Response.Listener {
                array=it
                for (i in 0 until array.length()) {
                    val user = array.getJSONObject(i)
                    if (usersViewModel.getUser(user.getString("email")) != "") {
                        usersViewModel.saveUser(User(user.getString("email"), ""))
                        Log.println(Log.INFO, null, "save " + user.getString("name"))
                    } else {
                        Log.println(Log.INFO, null, "dont save " + user.getString("name"))
                    }
                }
            },
            Response.ErrorListener {
                Log.println(Log.INFO, null, " " + it.message)
                Toast.makeText(this, "Error of authentication"+ it.message, Toast.LENGTH_SHORT).show()
            })
        {

            // override getHeader for pass session to service
            override fun getHeaders(): MutableMap<String, String> {

                val header = mutableMapOf<String, String>()
                try {

                    header.put("Content-Type", "application/json")
                    header.put("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xOTIuMTY4LjEwMy4yMTA6ODAwMFwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU3NDc4MzIwNSwiZXhwIjoxNTc0Nzg2ODA1LCJuYmYiOjE1NzQ3ODMyMDUsImp0aSI6InNGVHgxVkZ5MHVjaXdndXQiLCJzdWIiOjEyLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.8r8zN8MruHwRUk6RluUio2h7pvFj_p0FR8jHlfWyJdw")
                } catch (e: StackOverflowError) {
                    Log.println(Log.INFO, null, e.message.toString())
                }
                return header
            }
        }
        updateQueue.add(updateReq)


    }*/

}
