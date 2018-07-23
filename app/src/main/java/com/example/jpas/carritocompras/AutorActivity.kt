package com.example.andreavillacis.av_exammoviles_iib.Conductor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.andreavillacis.av_exammoviles_iib.Listar.ListarConductoresActivity
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
            textViewEntreador.text = "Editar Autor"
            autor = intent.getParcelableExtra("autor")
            fillFields()
            tipo = true
        }
        btnGuardarTienda.setOnClickListener { v: View? ->
            crearTienda()
        }
    }

    fun fillFields() {
        txtNombreTienda.setText(autor?.nombre)
        txtDireccionTienda.setText(autor?.apellido)
        txtFechaAperturaTienda.setText(autor?.fechaNacimiento)
        txtNumeroRuc.setText(autor?.numeroLibros.toString())
        if (autor?.ecuatoriano == 1) {
            switchMatriz.toggle()
        }
    }

    fun crearTienda(){
        var nombreT = txtNombreTienda.text.toString()
        var direccion = txtDireccionTienda.text.toString()
        var fechaA = txtFechaAperturaTienda.text.toString()
        var RUCs = txtNumeroRuc.text.toString().toInt()
        var matriz = if (switchMatriz.isChecked) 1 else 0

        if (!tipo){

            var tienda = Autor(0, nombreT, direccion, fechaA, RUCs, matriz, 0, 0)
            DatabaseAutor.insertarAutor(tienda)

        }else{
            var tienda = Autor(autor?.id!!, nombreT, direccion, fechaA, RUCs, matriz, 0, 0)
            DatabaseAutor.actualizaAutor(tienda)
        }
        irListarEntrenadorActivity()
    }

    fun irListarEntrenadorActivity(){
        val intent = Intent(this, ListarConductoresActivity::class.java)
        startActivity(intent)
    }
}
