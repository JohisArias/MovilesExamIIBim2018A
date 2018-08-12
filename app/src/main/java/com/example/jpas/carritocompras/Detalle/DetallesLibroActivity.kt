package com.example.jpas.carritocompras.Detalle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.jpas.carritocompras.EntidadesParcelable.Libro
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_detalles_libro.*


class DetallesLibroActivity : AppCompatActivity() {

    var libro: Libro? = null
    lateinit var myBitmapAgain:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_libro)

        libro = intent.getParcelableExtra("detallesLibro")


        txtShowIsbn.text = libro?.isbn.toString()
        txtShowNombreLibro.text = libro?.nombre
        txtShowEditorial.text = libro?.nombreEditorial
        txtShowPrecio.text = libro?.precio
        txtShowNumeroPaginas.text = libro?.numeroPaginas.toString()
        txtShowFechaPublicacion.text = libro?.fechaPublicacion.toString()

        myBitmapAgain = decodeBase64(libro?.imagenLibro.toString()!!)
        showImageViewLibro.setImageBitmap(myBitmapAgain)
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
