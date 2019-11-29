package com.example.series

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.series.authentication.model.User
import com.example.series.authentication.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usersViewModel = run {
            ViewModelProviders.of(this).get(UsersViewModel::class.java)
        }

        rg_registerText.setOnClickListener(){
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        rg_register.setOnClickListener() {
            addUser()

        }
    }

    private fun addUser() {

        // User input
        val loginJsonobj = JSONObject()

        loginJsonobj.put("name", rg_name.text)
        loginJsonobj.put("email", rg_email.text)
        loginJsonobj.put("password", rg_password.text)

        // new Volley newRequestQueue
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.103.210:8000/api/auth/register"
        val req = object : JsonObjectRequest(
            Request.Method.POST, url, loginJsonobj,
            Response.Listener {

                usersViewModel.saveUser(
                    User(
                        rg_name.text.toString(),
                        rg_email.text.toString()
                    )
                )

                Toast.makeText(this, "Tu cuenta ha sido creada con éxito ", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener {
                Toast.makeText(this, "No se ha podido crear la cuenta!", Toast.LENGTH_SHORT).show()
            }) {}

        queue.add(req)


    }
}
