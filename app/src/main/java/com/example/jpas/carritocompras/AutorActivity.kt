package com.example.andreavillacis.av_exammoviles_iib.Conductor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.andreavillacis.av_exammoviles_iib.Listar.ListarAutoresActivity
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseAutor
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_autor.*

class AutorActivity : AppCompatActivity() {

    var autor: Autor? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autor)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewAutor.text = "Editar Autor"
            autor = intent.getParcelableExtra("autor")
            fillFields()
            tipo = true
        }
        btnGuardarAutor.setOnClickListener { v: View? ->  crearAutor()
        }
    }

    fun fillFields() {
        txtNombreAutor.setText(autor?.nombre)
        txtApellido.setText(autor?.apellido)
        txtFechaNacimiento.setText(autor?.fechaNacimiento)
        txtNumeroLibros.setText(autor?.numeroLibros.toString())
        if (autor?.ecuatoriano == 1) {
            switchEcuatoriano.toggle()
        }
    }

    fun crearAutor(){
        var nombreAutor = txtNombreAutor.text.toString()
        var apellido = txtApellido.text.toString()
        var fechaNacimiento = txtFechaNacimiento.text.toString()
        var numeroLibros = txtNumeroLibros.text.toString().toInt()
        var ecuatoriano = if (switchEcuatoriano.isChecked) 1 else 0

        if (!tipo){

            var autor = Autor(0, nombreAutor, apellido, fechaNacimiento, numeroLibros, ecuatoriano, 0, 0)
            DatabaseAutor.insertarAutor(autor)

        }else{
            var autor = Autor(autor?.id!!, nombreAutor, apellido, fechaNacimiento, numeroLibros, ecuatoriano, 0, 0)
            DatabaseAutor.actualizaAutor(autor)
        }
        irListarAutorActivity()
    }

    fun irListarAutorActivity(){
        val intent = Intent(this, ListarAutoresActivity::class.java)
        startActivity(intent)
    }
}
