package com.example.series

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.User
import com.example.series.authentication.viewModel.UsersViewModel
import org.json.JSONObject

class VolleyRequest {
    companion object {
        private var db: TvShowDatabase? = null
        const val URL ="http://192.168.103.210:8000"

        @JvmStatic private fun getTokenUser(context: Context, usersViewModel: UsersViewModel) {

            val listUser=usersViewModel.users?.value
            if(listUser != null){
                for(i in 0 until listUser.size){
                    if(listUser[i].token!=""){
                        Log.println(Log.INFO,null,"token "+listUser[i].token)
                        //return listUser[i].token
                    }
                }
            }else{
                Log.println(Log.INFO,null,"get token user")
            }

            //return ""
        }

        @JvmStatic fun login(context: Context, email_text: EditText, password_text:EditText, usersViewModel: UsersViewModel) {

            val loginJsonobj = JSONObject()

            loginJsonobj.put("email", email_text.text)
            loginJsonobj.put("password", password_text.text)

            // new Volley newRequestQueue
            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/auth/login"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {
                    usersViewModel.updateToken(User(email_text.text.toString(), it.getString("token")))
                    val token=usersViewModel.getToken(email_text.text.toString())
                    Toast.makeText(context, "Sesión iniciada correctamente ", Toast.LENGTH_SHORT).show()

                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            ){}

            queue.add(req)

        }
        @JvmStatic fun logout(context: Context,usersViewModel: UsersViewModel){
            val listUser=usersViewModel.users?.value
            if(listUser != null){
                for(i in 0 until listUser.indices.count()){
                    if(listUser[i].token!=""){
                        usersViewModel.updateToken(User(listUser[i].email, ""))
                        Log.println(Log.INFO,null,"usuario "+listUser[i].email+" ya no esta logeado")

                    }
                }
            }else{
                Log.println(Log.INFO,null,"logout")
            }
            VolleyRequest.getTokenUser(context,usersViewModel)

        }

        fun addUser(context: Context,usersViewModel: UsersViewModel,name_editText:EditText,email_editText:EditText,password_editText:EditText) {

            val loginJsonobj = JSONObject()

            loginJsonobj.put("name", name_editText.text)
            loginJsonobj.put("email", email_editText.text)
            loginJsonobj.put("password", password_editText.text)

            val queue = Volley.newRequestQueue(context)
            val url = URL +"/api/auth/register"
            val req = object : JsonObjectRequest(
                Request.Method.POST, url, loginJsonobj,
                Response.Listener {

                    usersViewModel.saveUser(User(email_editText.text.toString(),""))

                    Toast.makeText(context, "Registro realizado correctamente ! ", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error al registrarse !", Toast.LENGTH_SHORT).show()
                })
            {}

            queue.add(req)
        }

    }

}