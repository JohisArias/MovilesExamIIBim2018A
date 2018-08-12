package com.example.jpas.carritocompras.Listar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.jpas.carritocompras.AutorClienteAdapter
import com.example.jpas.carritocompras.EntidadesParcelable.Autor
import com.example.jpas.carritocompras.R
import com.example.jpas.carritocompras.Database.DatabaseAutor
import kotlinx.android.synthetic.main.activity_listar_autor_cliente.*

class ListarAutorClienteActivity : AppCompatActivity() {

    lateinit var adaptador: AutorClienteAdapter
    lateinit var autors: ArrayList<Autor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_autor_cliente)

        autors = DatabaseAutor.getList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = AutorClienteAdapter(autors)
        recyclerView_listarAutores_Clientes.layoutManager = layoutManager
        recyclerView_listarAutores_Clientes.itemAnimator = DefaultItemAnimator()
        recyclerView_listarAutores_Clientes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_listarAutores_Clientes)
    }
}
