package com.example.jpas.carritocompras

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_libro.*
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.jpas.carritocompras.EntidadesParcelable.Libro
import com.example.jpas.carritocompras.Database.DatabaseLibro

class LibroActivity : AppCompatActivity() {

    var libro: Libro? = null
    var idAutor: Int = 0
    private lateinit var imageBitmap: Bitmap
    var tipo = false
    lateinit var myBase64Image:String
    lateinit var myBitmapAgain:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro)

        idAutor = intent.getIntExtra("autorId", 0)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewLibro.text = "Editar Libro"
            libro = intent.getParcelableExtra("libro")
            fillFields()
            tipo = true
        }

        btnGuardarLibro.setOnClickListener { v: View? ->  crearLibro()
        }

        btnTomarFoto.setOnClickListener{v: View? -> tomarFoto()

        }
    }

    fun fillFields() {
        txtIsbn.setText(libro?.isbn.toString())
        txtNombreLibro.setText(libro?.nombre)
        txtEditorial.setText(libro?.nombreEditorial)
        txtPrecio.setText(libro?.precio)
        txtNumeroPaginas.setText(libro?.numeroPaginas!!)
        txtFechaPublicacion.setText(libro?.fechaPublicacion.toString())
    }

    fun crearLibro(){
        var isbn = txtIsbn.text.toString().toInt()
        var nombreLibro = txtNombreLibro.text.toString()
        var nombreEditorial = txtEditorial.text.toString()
        var precio = txtPrecio.text.toString()
        var numeroPaginas = txtNumeroPaginas.text.toString().toInt()
        var fechaPublicacion = txtFechaPublicacion.text.toString()
        var imagenLibro = myBase64Image

        if (!tipo){
            var libro = Libro(0, isbn, nombreLibro, nombreEditorial, precio, numeroPaginas, fechaPublicacion, imagenLibro, idAutor, 0, 0)
            DatabaseLibro.insertarLibro(libro)

        }else{
            var libro = Libro(libro?.id!!, isbn, nombreLibro, nombreEditorial, precio, numeroPaginas, fechaPublicacion, imagenLibro, idAutor, 0, 0)
            DatabaseLibro.actualizaLibro(libro)
        }
        finish()
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun tomarFoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
       super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap
            myBase64Image = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100)
            myBitmapAgain = decodeBase64(myBase64Image)

            imageViewLibro.setImageBitmap(myBitmapAgain)
        }
    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return android.util.Base64.encodeToString(byteArrayOS.toByteArray(), android.util.Base64.DEFAULT)
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}

