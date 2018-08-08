package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.andreavillacis.av_exammoviles_iib.Auto.LibroClienteAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseLibro
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_detalles_autor.*

class DetallesAutorClienteActivity : AppCompatActivity() {

    var autor: Autor? = null
    lateinit var libro: ArrayList<Libro>
    lateinit var adaptador: LibroClienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_autor_cliente)

        autor = intent.getParcelableExtra("detallesAutorCliente")

        txtShowIdAutor.text = autor?.id.toString()
        txtShowNombreAutor.text = autor?.nombre
        txtShowApellido.text = autor?.apellido
        txtShowFechaNacimiento.text = autor?.fechaNacimiento
        txtShowNumeroLibros.text = autor?.numeroLibros.toString()
        txtShowEcuatoriano.text = if(autor?.ecuatoriano == 1) "Si" else "No"

        libro = DatabaseLibro.getLibroList(autor?.id!!)
        Log.d("resultado",libro.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = LibroClienteAdapter(libro)
        recycler_view_libro.layoutManager = layoutManager
        recycler_view_libro.itemAnimator = DefaultItemAnimator()
        recycler_view_libro.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_libro)
    }
}
