package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.andreavillacis.av_exammoviles_iib.Conductor.AutorClienteAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseAutor
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_buscar_autor.*

class BuscarAutorActivity : AppCompatActivity() {

    lateinit var adaptador: AutorClienteAdapter
    lateinit var autors: ArrayList<Autor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_autor)

        btnBuscarAutorServer.setOnClickListener { v: View? ->
            consultarDatos()
        }
    }

    fun consultarDatos(){
        if (txtBuscarTienda.equals("")){
            Toast.makeText(this,"Ingrese parametro de busqueda",Toast.LENGTH_SHORT).show()
        }else{
            var datoBusqueda:String = txtBuscarTienda.text.toString()

            autors = DatabaseAutor.buscarAutor(datoBusqueda)

            val layoutManager = LinearLayoutManager(this)
            adaptador = AutorClienteAdapter(autors)
            recyclerView_Resultados_Entrenador.layoutManager = layoutManager
            recyclerView_Resultados_Entrenador.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Entrenador.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Entrenador)

        }
    }
}
