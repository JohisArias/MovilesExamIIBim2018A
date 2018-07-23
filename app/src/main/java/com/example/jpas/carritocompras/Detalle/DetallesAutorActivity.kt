package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.Auto.LibroActivity
import com.example.andreavillacis.av_exammoviles_iib.Auto.LibroAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseLibro
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_detalles_autor.*

class DetallesAutorActivity : AppCompatActivity() {

    var autor: Autor? = null
    lateinit var libro: ArrayList<Libro>
    lateinit var adaptador: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_autor)

        autor = intent.getParcelableExtra("detallesAutor")

        txtShowIdTienda.text = autor?.id.toString()
        txtShowNombreT.text = autor?.nombre
        txtShowDireccionT.text = autor?.apellido
        txtShowFechaApertura.text = autor?.fechaNacimiento
        txtShowNRUCS.text = autor?.numeroLibros.toString()
        txtShowMat.text = if(autor?.ecuatoriano == 1) "Si" else "No"

        libro = DatabaseLibro.getLibroList(autor?.id!!)
        Log.d("resultado",libro.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = LibroAdapter(libro)
        recycler_view_producto.layoutManager = layoutManager
        recycler_view_producto.itemAnimator = DefaultItemAnimator()
        recycler_view_producto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_producto)

        btnNuevoLibro.setOnClickListener { v: View? ->
            irActividdadCrearLibro()
        }
    }

    fun irActividdadCrearLibro(){
        val intent = Intent(this, LibroActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("autorId", autor?.id!!)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var producto1 = libro[position]

        when (item.itemId) {
            R.id.item_menu_editar -> {
                val intent = Intent(this, LibroActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("libro", producto1)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Desea eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseLibro.eliminarLibro(producto1.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
