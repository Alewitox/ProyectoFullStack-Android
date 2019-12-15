package com.example.series

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.series.authentication.database.TvShowDatabase
import com.example.series.authentication.model.User
import com.example.series.authentication.viewModel.UsersViewModel
import org.json.JSONArray
import org.json.JSONObject

class VolleyRequestUser {

    companion object {
        private var db: TvShowDatabase? = null
        const val URL ="http://192.168.1.210:8000"


            @JvmStatic fun login(context: Context,email_text:EditText,password_text:EditText,usersViewModel: UsersViewModel) {

                val loginJsonobj = JSONObject()

                loginJsonobj.put("email", email_text.text)
                loginJsonobj.put("password", password_text.text)

                // new Volley newRequestQueue
                val queue = Volley.newRequestQueue(context)
                val url = URL + "/api/auth/login"
                val req = object : JsonObjectRequest(Request.Method.POST, url, loginJsonobj,
                    Response.Listener {
                        updateToken(context,email_text,usersViewModel,it.getString("token"))
                        Toast.makeText(context, "Sesión iniciada correctamente ", Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener {
                        Toast.makeText(context, "Error al iniciar sesión ", Toast.LENGTH_SHORT).show()
                    }
                ){}
                queue.add(req)
            }

            @JvmStatic fun logout(context: Context,usersViewModel: UsersViewModel){
                usersViewModel.updateToken(User(1,"",""))
                val intent = Intent(context,LoginActivity::class.java)
                context.startActivity(intent)
            }

            @JvmStatic fun updateToken(context: Context,email_text:EditText,usersViewModel: UsersViewModel,token:String) {

                val jsonObject = JSONObject()

                jsonObject.put("email",email_text.text.toString())

                // new Volley newRequestQueue
                val queue = Volley.newRequestQueue(context)
                val url = URL + "/user"
                val req = object : JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    Response.Listener {
                        usersViewModel.updateUser(User(1, it.getString("id"), token))

                        val intent = Intent(context,HomeActivity::class.java)
                        context.startActivity(intent)

                    },
                    Response.ErrorListener {
                        Log.println(Log.INFO,null,"ERROR " + it.message)
                    }
                ){}
                queue.add(req)
            }


            @JvmStatic fun registerUser(context: Context,name_editText:EditText,email_editText:EditText,password_editText:EditText) {

                val loginJsonobj = JSONObject()

                loginJsonobj.put("name", name_editText.text)
                loginJsonobj.put("email", email_editText.text)
                loginJsonobj.put("password", password_editText.text)

                val queue = Volley.newRequestQueue(context)
                val url = URL  + "/api/auth/register"
                val req = object : JsonObjectRequest(
                    Request.Method.POST, url, loginJsonobj,
                    Response.Listener {

                        Toast.makeText(context, "Usuario registrado correctamente ", Toast.LENGTH_LONG).show()

                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    },
                    Response.ErrorListener {
                        Log.println(Log.INFO,null,"ERROR " + it.networkResponse)
                    })
                {}

                queue.add(req)
            }

            @JvmStatic fun selectUser(context: Context, usersViewModel: UsersViewModel, profileTextView: TextView, headerEmail:TextView, aliasTextView:TextView, emailTextView:TextView) {


                val queue = Volley.newRequestQueue(context)
                val url = URL + "/api/users/" + usersViewModel.getUserId(1)
                val req = object : JsonObjectRequest(
                    Request.Method.GET, url, null,
                    Response.Listener {

                        profileTextView.setText(it.getString("name"))
                        headerEmail.setText(it.getString("email"))
                        aliasTextView.setText(it.getString("name"))
                        emailTextView.setText(it.getString("email"))

                    },
                    Response.ErrorListener {
                        Log.println(Log.INFO,null,"ERROR " + it.networkResponse.data)
                    })
                {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers: MutableMap<String, String> = HashMap()
                        // Basic Authentication
                        var token = usersViewModel.getToken(1)
                        headers["Authorization"] = "Bearer " + token
                        return headers
                    }
                }

                queue.add(req)
            }

            @JvmStatic fun updateUser(context: Context,usersViewModel: UsersViewModel,editAlias:TextView, editEmail:TextView) {

                val updateJsonobj = JSONObject()

                updateJsonobj.put("name", editAlias.text)
                updateJsonobj.put("email", editEmail.text)

                val queue = Volley.newRequestQueue(context)
                val url = URL +"/api/users/"+usersViewModel.getUserId(1)
                val req = object : JsonObjectRequest(
                    Request.Method.PUT, url, updateJsonobj,
                    Response.Listener {
                        Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_LONG).show()

                        val intent=Intent(context,ProfileActivity::class.java)
                        context.startActivity(intent)

                    },
                    Response.ErrorListener {
                        Log.println(Log.INFO,null,"ERROR " + it.message)
                    })
                {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers: MutableMap<String, String> = HashMap()
                        // Basic Authentication
                        var token = usersViewModel.getToken(1)
                        headers["Authorization"] = "Bearer " + token
                        return headers
                    }
                }

                queue.add(req)
            }

            @JvmStatic fun deleteUser(context: Context,usersViewModel: UsersViewModel) {

                val queue = Volley.newRequestQueue(context)
                val url = URL + "/api/users/" + usersViewModel.getUserId(1)
                val req = object : StringRequest(
                    Request.Method.DELETE, url,
                    Response.Listener {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()

                        val intent=Intent(context,LoginActivity::class.java)
                        context.startActivity(intent)

                    },
                    Response.ErrorListener {
                        Log.println(Log.INFO,null,"ERROR "+it.message)
                    })
                {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers: MutableMap<String, String> = HashMap()
                        // Basic Authentication
                        var token = usersViewModel.getToken(1)
                        headers["Authorization"] = "Bearer " + token
                        return headers
                    }
                }

                queue.add(req)
            }

    }

}