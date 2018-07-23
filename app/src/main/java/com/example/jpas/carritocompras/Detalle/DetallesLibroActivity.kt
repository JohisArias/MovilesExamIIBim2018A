package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
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
        txtShowNombreP.text = libro?.nombre
        txtShowEditorial.text = libro?.nombreEditorial
        txtShowPrecio.text = libro?.precio
        txtShowFechaL.text = libro?.numeroPaginas.toString()
        txtShowGar.text = libro?.fechaPublicacion.toString()

        myBitmapAgain = decodeBase64(libro?.imagenLibro.toString()!!)
        showImageViewPokemon.setImageBitmap(myBitmapAgain)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
