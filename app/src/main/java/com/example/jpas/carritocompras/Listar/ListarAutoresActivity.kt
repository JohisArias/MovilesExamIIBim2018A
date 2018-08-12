package com.example.jpas.carritocompras.Listar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.example.jpas.carritocompras.AutorActivity
import com.example.jpas.carritocompras.AutorAdapter
import com.example.jpas.carritocompras.EntidadesParcelable.Autor
import com.example.jpas.carritocompras.Database.DatabaseAutor
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_listar_autores.*

class ListarAutoresActivity : AppCompatActivity() {

    lateinit var adaptador: AutorAdapter
    lateinit var autors: ArrayList<Autor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_autores)

        autors = DatabaseAutor.getList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = AutorAdapter(autors)
        recyclerViewAutor.layoutManager = layoutManager
        recyclerViewAutor.itemAnimator = DefaultItemAnimator()
        recyclerViewAutor.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerViewAutor)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var tienda = autors[position]

        when (item.itemId) {
            /*R.id.item_menu_compartir -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.autor)} - ${getString(R.string.app_name)}")
                intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.name)} ${autor.nombre} ${autor.editorial}\n${getString(R.string.numero_libros)} ${autor.RUC}\n${getString(R.string.fecha_nacimiento)} ${autor.fechaNacimiento}")
                startActivity(intent)
                return true
            }*/
            R.id.item_menu_editar -> {
                val intent = Intent(this, AutorActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("autor", tienda)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseAutor.eliminarAutor(tienda.id)
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
