package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

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


        val ciudadmx= Ciudad("Ciudad de Mexico", 15, "Soleado")
        val ciudadBerlin= Ciudad("Berlin", 30, "Cielo despejado")

        if(ciudad =="ciudad-mexico"){
           tvCiudad?.text = ciudadmx.nombre
            tvGrados?.text= ciudadmx.grados.toString() +"°"
            tvEstatus?.text=ciudadmx.estatus
        }else if(ciudad == "ciudad-berlin"){
            tvCiudad?.text = ciudadBerlin.nombre
            tvGrados?.text= ciudadBerlin.grados.toString() +"°"
            tvEstatus?.text=ciudadBerlin.estatus
        }else{
            Toast.makeText(this, "No se encuentra la informacion ", Toast.LENGTH_SHORT).show()
        }
    }
}