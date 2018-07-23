package com.example.andreavillacis.av_exammoviles_iib.Listar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.andreavillacis.av_exammoviles_iib.Conductor.AutorClienteAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.daro.carritocompras.R
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseAutor
import kotlinx.android.synthetic.main.activity_listar_autor_cliente.*

class ListarConductorClienteActivity : AppCompatActivity() {

    lateinit var adaptador: AutorClienteAdapter
    lateinit var autors: ArrayList<Autor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_conductor_cliente)

        autors = DatabaseAutor.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = AutorClienteAdapter(autors)
        recyclerView_listarEntrenadores_Clientes.layoutManager = layoutManager
        recyclerView_listarEntrenadores_Clientes.itemAnimator = DefaultItemAnimator()
        recyclerView_listarEntrenadores_Clientes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_listarEntrenadores_Clientes)
    }
}
