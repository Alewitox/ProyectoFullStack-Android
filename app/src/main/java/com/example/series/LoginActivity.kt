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

        lg_loginText.setOnClickListener{
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        lg_login.setOnClickListener{
            RequestHttp.login(this, lg_email, lg_password, usersViewModel)
            //syncUpUsers()

        }
    }


}
