package com.example.series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.series.authentication.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_home.toolbar
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var usersViewModel : UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }


        logoutClick.setOnClickListener{
            VolleyRequestUser.logout(this, usersViewModel)
        }

        deleteAccount.setOnClickListener{
            VolleyRequestUser.deleteUser(this, usersViewModel)
        }

        editProfileBtn.setOnClickListener{
            var intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        VolleyRequestUser.selectUser(this,usersViewModel,profileTextView, headerEmail, aliasTextView, emailTextView)
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
