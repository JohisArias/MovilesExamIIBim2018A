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
    lateinit var autor: ArrayList<Autor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_autor)

        btnBuscarAutorServer.setOnClickListener { v: View? ->
            consultarDatos()
        }
    }

    fun consultarDatos(){
        if (txtBuscarAutor.equals("")){
            Toast.makeText(this,"Ingrese parametro de busqueda",Toast.LENGTH_SHORT).show()
        }else{
            var datoBusqueda:String = txtBuscarAutor.text.toString()

            autor = DatabaseAutor.buscarAutor(datoBusqueda)

            val layoutManager = LinearLayoutManager(this)
            adaptador = AutorClienteAdapter(autor)
            recyclerView_Resultados_Autor.layoutManager = layoutManager
            recyclerView_Resultados_Autor.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Autor.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Autor)

        }
    }
}
