package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.Conductor.AutorActivity
import com.example.andreavillacis.av_exammoviles_iib.Listar.ListarAutoresActivity
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_menu.*


class RegistrarUsuarios : AppCompatActivity() {

    lateinit var rol:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        rol = intent.getStringExtra("valorRol")

        if (rol.equals("VENDEDOR",true)){
            btnBuscarEntrenador.visibility = View.INVISIBLE

        }else if (rol.equals("DELIVERY",true)){
            btnBuscarEntrenador.visibility = View.INVISIBLE
            btnCrearEntrenador.visibility = View.INVISIBLE
            btnListarEntrenador.visibility = View.INVISIBLE
            irActividadDelivery()

        }else{
            btnCrearEntrenador.visibility = View.INVISIBLE
            btnListarEntrenador.visibility = View.INVISIBLE
        }

      btnCrearEntrenador.setOnClickListener { v: View? ->
          irAutorActivity()
      }

        btnListarEntrenador.setOnClickListener { v: View? ->
            irListarAutoresActivity()
        }

        btnBuscarEntrenador.setOnClickListener { v: View? ->
            irBuscarAutorActivity()
        }
    }

    fun irAutorActivity(){
        val intent = Intent(this, AutorActivity::class.java)
        intent.putExtra("tipo", "Create")
        startActivity(intent)
    }

    fun irListarAutoresActivity(){
        val intent = Intent(this, ListarAutoresActivity::class.java)
        startActivity(intent)
    }

    fun irActividadDelivery(){
        val intent = Intent(this,DeliveryActivity::class.java)
        startActivity(intent)

    }
    fun irBuscarAutorActivity(){
        val intent = Intent(this,BuscarAutorActivity::class.java)
        startActivity(intent)
    }
}
