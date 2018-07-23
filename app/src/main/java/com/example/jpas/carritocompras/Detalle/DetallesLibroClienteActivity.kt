package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.daro.carritocompras.DatosAutorActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_detalles_libro_cliente.*

class DetallesLibroClienteActivity : AppCompatActivity() {

    var libro: Libro? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idLibro:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_libro_cliente)

        libro = intent.getParcelableExtra("detallesLibroCliente")

        txtShowIsbn.text = libro?.isbn.toString()
        txtShowNombreLibro.text = libro?.nombre
        txtShowEditorial.text = libro?.nombreEditorial
        txtShowPrecio.text = libro?.precio
        txtShowNumeroPaginas.text = libro?.numeroPaginas.toString()
        txtShowFechaPublicacion.text = libro?.fechaPublicacion.toString()

        myBitmapAgain = decodeBase64(libro?.imagenLibro.toString()!!)
        showImageViewLibro.setImageBitmap(myBitmapAgain)

        btnAdquirirProducto.setOnClickListener { v ->
            irActividadDatosComprador()
        }
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun irActividadDatosComprador(){
        val intent = Intent(this, DatosAutorActivity::class.java)
        idLibro = libro?.id.toString()
        intent.putExtra("idLibro",idLibro)
        startActivity(intent)
    }
}
