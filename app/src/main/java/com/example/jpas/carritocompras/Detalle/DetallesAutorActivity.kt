package com.example.jpas.carritocompras.Detalle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.jpas.carritocompras.LibroActivity
import com.example.jpas.carritocompras.LibroAdapter
import com.example.jpas.carritocompras.EntidadesParcelable.Libro
import com.example.jpas.carritocompras.EntidadesParcelable.Autor
import com.example.jpas.carritocompras.Database.DatabaseLibro
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

        txtShowIdAutor.text = autor?.id.toString()
        txtShowNombreAutor.text = autor?.nombre
        txtShowApellido.text = autor?.apellido
        txtShowFechaNacimiento.text = autor?.fechaNacimiento
        txtShowNumeroLibros.text = autor?.numeroLibros.toString()
        txtShowEcuatoriano.text = if(autor?.ecuatoriano == 1) "Si" else "No"

        libro = DatabaseLibro.getLibroList(autor?.id!!)
        Log.d("resultado",libro.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = LibroAdapter(libro)
        recycler_view_libro.layoutManager = layoutManager
        recycler_view_libro.itemAnimator = DefaultItemAnimator()
        recycler_view_libro.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_libro)

        btnNuevoLibro.setOnClickListener { v: View? ->  irActividdadCrearLibro()
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
        var libro1 = libro[position]

        when (item.itemId) {
            R.id.item_menu_editar -> {
                val intent = Intent(this, LibroActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("libro", libro1)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Desea eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseLibro.eliminarLibro(libro1.id)
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
