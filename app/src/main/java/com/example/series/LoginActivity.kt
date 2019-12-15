package com.example.series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.User
import com.example.series.authentication.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.toolbar

class LoginActivity : AppCompatActivity() {

    private var db: TvShowDatabase? = null
    private lateinit var token: String

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        setSupportActionBar(toolbar)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }



        if(usersViewModel.getUserIdLocal(1) == 0){
            usersViewModel.saveUser(User(1,"",""))
            Log.println(Log.INFO,null,"Guardado ")
        }else{
            Log.println(Log.INFO,null,"No guardado ")
        }

        lg_loginText.setOnClickListener{
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        lg_login.setOnClickListener{
            VolleyRequestUser.login(this, lg_email, lg_password, usersViewModel)


        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}


