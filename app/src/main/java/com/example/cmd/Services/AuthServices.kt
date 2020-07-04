package com.example.cmd.Services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cmd.Utilities.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

object AuthServices {

    var userId = ""
    var authToken = ""
    var isLoggedIn = false

    fun newRegistration(context: Context, username: String, password: String, complete: (Boolean) -> Unit){

        val jsonBody = JSONObject()
        jsonBody.put("username", username)
        jsonBody.put("password", password)
        val jsonString = jsonBody.toString()

        val registrationRequest = object : JsonObjectRequest(Method.POST, REGISTRATION_URL, null, Response.Listener { response ->
            println(response)
            complete(true)
            try{
                userId = response.getString("user")
                UserDataService.authToken = response.getString("token")
                isLoggedIn = true
                complete(true)
            } catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {error ->
            complete(false)
            Log.d("LOGIN ERROR", "Couldnt find user  $error")
        }){
            override fun getBody(): ByteArray {
                return jsonString.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        Volley.newRequestQueue(context).add(registrationRequest);

    }

    fun loginUser(context: Context, username: String, password: String, complete: (Boolean) -> Unit){
        val jsonBody = JSONObject()
        jsonBody.put("username", username)
        jsonBody.put("password", password)
        val jsonString = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(Method.POST, LOGIN_URL, null, Response.Listener { response ->
            println(response)
            try{
                userId = response.getString("user")
                UserDataService.authToken = response.getString("token")
                isLoggedIn = true
                complete(true)
            } catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {error ->
            complete(false)
            Log.d("LOGIN ERROR", "Couldnt Log in $error")
        }){
            override fun getBody(): ByteArray {
                return jsonString.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        Volley.newRequestQueue(context).add(loginRequest)
    }

    fun findUser(context: Context, token: String, complete: (Boolean) -> Unit){
        println("Toekn " + token)
        val findUserRequest = object : JsonObjectRequest(Method.GET, FIND_USER_URL, null, Response.Listener {response ->
            println(response)

            try{
                UserDataService.id = response.getString("_id")
                UserDataService.name = response.getString("name")
                UserDataService.surname = response.getString("surname")
                UserDataService.othername = response.getString("othername")
                UserDataService.email = response.getString("email")
                UserDataService.course = response.getString("course")
                UserDataService.date = response.getString("date")

                complete(true)

            }catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
            }
        }, Response.ErrorListener {error->
            Log.d("ERROR", "Could not find user $error")
            complete(false)
        })
        {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }


            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("auth-token", "${token}")
                return headers
            }
        }

        Volley.newRequestQueue(context).add(findUserRequest)

    }

    fun findDiningUser(context: Context){
        val findDiningUserRequest = object : JsonObjectRequest(Method.GET, FIND_DINING_USER_URL, null, Response.Listener {response ->
            println(response)
            try{
                DiningUserDataService.id = response.getString("_id")
                DiningUserDataService.email = response.getString("email")
                DiningUserDataService.status = response.getBoolean("status")

                val userDataChanged = Intent(BROADCAST_USER_DATA_CHANGED)
                LocalBroadcastManager.getInstance(context).sendBroadcast(userDataChanged)


            }catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
            }
        }, Response.ErrorListener {
            Log.d("ERROR", "Could not find dining user user")

        })
        {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }


            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("auth-token", "${UserDataService.authToken}")
                return headers
            }
        }

        Volley.newRequestQueue(context).add(findDiningUserRequest)

    }

    fun findDiningData(context: Context){


        val findDiningDataRequest = object : JsonArrayRequest(Method.GET, FIND_DINING_DATA_URL, null, Response.Listener {response ->
            println(response)
            val diningData: JSONObject = response[0] as JSONObject
            println(diningData)
            try{
                DiningDataServices.id = diningData.getString("_id")
                DiningDataServices.diningCode = diningData.getString("diningCode")
                DiningDataServices.course = diningData.getString("course")

            }catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
            }
        }, Response.ErrorListener { error->
            Log.d("ERROR", "Could not find dining data : $error")

        })
        {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        Volley.newRequestQueue(context).add(findDiningDataRequest)

    }

    fun updateDinnerUser(context: Context, email: String, complete: (Boolean) -> Unit){

        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("status", false)

        val jsonString = jsonObject.toString()

        val updateDinnerUserRequest = object : JsonObjectRequest(Method.POST, UPDATE_DINING_USER_URL, null, Response.Listener {response ->
            println(" updateDinnerUserRequest " + response)
            try{
                DiningUserDataService.id = response.getString("_id")
                DiningUserDataService.email = response.getString("email")
                DiningUserDataService.status = response.getBoolean("status")
                complete(true)
            } catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener { error ->
            Log.d("ERR", "coudnt update user $error")
            complete(false)
        }){
            override fun getBody(): ByteArray {
                return jsonString.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(updateDinnerUserRequest)
    }


    fun registerProfile(context: Context, name: String, surname: String, othername:String, token: String,  complete: (Boolean) -> Unit){

        println("Regiser profile" + token)
        val jsonBody = JSONObject()

        jsonBody.put("name", name)
        jsonBody.put("surname", surname)
        jsonBody.put("othername", othername)


        val jsonString = jsonBody.toString()

        val registerProfileRequest = object : JsonObjectRequest(Method.POST, REGISTER_PROFILE_URL, null, Response.Listener {response ->

            println(response)
            complete(true)

        }, Response.ErrorListener {error ->
            Log.d("REGERR", "Couldnt add new profile failed: $error");
        }){

            override fun getBody(): ByteArray {
                return jsonString.toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("auth-token", "${token}")
                return headers
            }
        }


        Volley.newRequestQueue(context).add(registerProfileRequest);

    }




}