package com.example.series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_login.*

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
            VolleyRequest.login(this, lg_email, lg_password, usersViewModel)


        }
    }


}
