package com.example.jpas.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jpas.carritocompras.Listar.ListarAutoresActivity
import kotlinx.android.synthetic.main.activity_menu.*


class RegistrarUsuarios : AppCompatActivity() {

    lateinit var rol:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        rol = intent.getStringExtra("valorRol")

        if (rol.equals("VENDEDOR",true)){
            btnBuscarAutor.visibility = View.INVISIBLE

        }else if (rol.equals("DELIVERY",true)){
            btnBuscarAutor.visibility = View.INVISIBLE
            btnCrearAutor.visibility = View.INVISIBLE
            btnListarAutor.visibility = View.INVISIBLE
            irActividadDelivery()

        }else{
            btnCrearAutor.visibility = View.INVISIBLE
            btnListarAutor.visibility = View.INVISIBLE
        }

      btnCrearAutor.setOnClickListener { v: View? ->
          irAutorActivity()
      }

        btnListarAutor.setOnClickListener { v: View? ->
            irListarAutoresActivity()
        }

        btnBuscarAutor.setOnClickListener { v: View? ->
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
