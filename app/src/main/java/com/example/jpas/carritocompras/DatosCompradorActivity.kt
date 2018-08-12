package com.example.jpas.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_datos_comprador.*
import android.content.Intent
import android.widget.Toast
import com.example.jpas.carritocompras.EntidadesParcelable.OrdenCompra
import com.example.jpas.carritocompras.Database.DatabaseOrdenCompra


class DatosCompradorActivity : AppCompatActivity() {

    lateinit var idLibro:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_comprador)

       idLibro = intent.getStringExtra("idLibro")

        button_ubicacion.setOnClickListener{ v: View? ->
            Toast.makeText(this, "Ubicacion enviada exitosamente", Toast.LENGTH_LONG).show();
        }

        btnEnviarDatosComprador.setOnClickListener { v: View? ->
            crearOreden()
        }
    }

    fun crearOreden(){
        var cedula = txtCedulaComprador.text.toString().toInt()
        var sector = txtSector.text.toString()
        var idLibro = idLibro.toString().toInt()

        var oredenCompra = OrdenCompra(0, cedula, sector, idLibro)
        DatabaseOrdenCompra.insertarOrden(oredenCompra)

        Alerter.create(this)
                .setTitle("Datos enviados a DELIVERY")
                .setText("Orden enviada exitosamente")
                .setDuration(10000)
                .enableSwipeToDismiss()
                .setOnClickListener(View.OnClickListener {
                    irABuscarAutor()
                }).show()
    }

    fun irABuscarAutor(){
        txtCedulaComprador.setText("")
        txtSector.setText("")
        val intent = Intent(this,BuscarAutorActivity::class.java)
        startActivity(intent)
    }
}
