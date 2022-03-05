package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.solicitudeshttp.Network
import java.lang.Exception
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    var tvCiudad:TextView?= null
    var tvGrados:TextView?= null
    var tvEstatus:TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad= findViewById(R.id.tvCiudad)
        tvGrados= findViewById(R.id.tvGrados)
        tvEstatus= findViewById(R.id.tvEstatus)

        val ciudad= intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")
        if(Network.hayRed(this)){
            SolVolley("http://api.openweathermap.org/data/2.5/weather?id="+ciudad+"&appid=8adbaa8b2520c3778e2c036921f57eb0&units=metric&lang=es")
            //8adbaa8b2520c3778e2c036921f57eb0
        }else{
            Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()

        }
    }

    fun SolVolley(url: String) {
        val cola = Volley.newRequestQueue(this)
        val solicitud =StringRequest(Request.Method.GET, url, Response.Listener<String>{ response ->
            try {
                Log.d("solHTTP", response)
                val gson= Gson()
                val ciudad=gson.fromJson(response, Ciudad::class.java)
                Log.d("GSON",ciudad.name)
                tvCiudad?.text = ciudad.name
                tvGrados?.text= ciudad.main?.temp.toString() +"°"
                tvEstatus?.text=ciudad.weather?.get(0)?.description

            } catch (e: Exception) {

            }
        }, Response.ErrorListener { })
        cola.add(solicitud)
    }
}