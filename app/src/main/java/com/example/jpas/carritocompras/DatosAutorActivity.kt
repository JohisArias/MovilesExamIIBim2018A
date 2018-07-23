package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_datos_comprador.*
import android.content.Intent
import android.widget.Toast
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseOrdenCompra
import com.example.jpas.carritocompras.R


class DatosAutorActivity : AppCompatActivity() {

    lateinit var idLibro:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_comprador)

       idLibro = intent.getStringExtra("idLibro")

        //Toast.makeText(this,idLibro,Toast.LENGTH_SHORT).show()
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
        var idProducto = idLibro.toString().toInt()

        var oredenCompra = OrdenCompra(0, cedula, sector, idProducto)
        DatabaseOrdenCompra.insertarOrden(oredenCompra)

        Alerter.create(this)
                .setTitle("Datos Enviados a DELIVERY")
                .setText("Orden enviada exitosamente")
                .setDuration(10000)
                .enableSwipeToDismiss()
                .setOnClickListener(View.OnClickListener {
                    irAbuscarAutor()
                }).show()
    }

    fun irAbuscarAutor(){
        txtCedulaComprador.setText("")
        txtSector.setText("")
        val intent = Intent(this,BuscarAutorActivity::class.java)
        startActivity(intent)
    }
}
