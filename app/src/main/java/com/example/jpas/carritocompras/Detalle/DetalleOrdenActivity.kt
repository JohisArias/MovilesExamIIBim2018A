package com.example.jpas.carritocompras.Detalle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jpas.carritocompras.EntidadesParcelable.OrdenCompra
import com.example.jpas.carritocompras.EntidadesParcelable.OrdenDetalles
import com.example.jpas.carritocompras.Database.DatabaseOrdenCompra
import com.example.jpas.carritocompras.MapsActivity
import com.example.jpas.carritocompras.R
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_detalle_orden.*


class DetalleOrdenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var oredenes: OrdenCompra? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_orden)

        oredenes = intent.getParcelableExtra("detallesOrden")

        txtShowCedula.text = oredenes?.cedulaComprador.toString()
        txtShowSector.text = oredenes?.sector
        txtShowIdLibro.text = oredenes?.idLibro.toString()

        button_mapa.setOnClickListener{v: View? ->
            irActividadMapas()
        }
        btnGuardarDatosOrden.setOnClickListener { v: View? ->
            guardarDatosOrdenDetalles()
        }
    }

    fun irActividadMapas(){
        val intent= Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun guardarDatosOrdenDetalles(){
        val fechaEnvio = txtFechaEnvio.text.toString()
        val costoLibro = txtPrecioLibro.text.toString().toInt()
        val idLibro = txtShowIdLibro.text.toString().toInt()
        val ordenDetalles = OrdenDetalles(0, fechaEnvio, costoLibro, idLibro)
        DatabaseOrdenCompra.insertarOrdenDetalles(ordenDetalles)
        Alerter.create(this)
                .setTitle("Orden enviada a CLIENTE")
                .setText("La solicitud fue enviada exitosamente")
                .enableSwipeToDismiss()
                .show()
    }
}
