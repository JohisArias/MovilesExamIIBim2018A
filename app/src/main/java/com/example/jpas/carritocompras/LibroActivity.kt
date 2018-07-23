package com.example.andreavillacis.av_exammoviles_iib.Auto

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
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseLibro
import com.example.jpas.carritocompras.R


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
            textViewPokemon.text = "Editar Libro"
            libro = intent.getParcelableExtra("libro")
            fillFields()
            tipo = true
        }

        btnGuardarLibro.setOnClickListener { v: View? ->
            crearProducto()
        }

        btnTomarFoto.setOnClickListener{v: View? ->
            tomarFoto()

        }
    }

    fun fillFields() {
        txtNumeroProd.setText(libro?.isbn.toString())
        txtNombreProd.setText(libro?.nombre)
        txtDescripcionProd.setText(libro?.nombreEditorial)
        txtPrecioProd.setText(libro?.precio)
        txtFechaLanzamiento.setText(libro?.numeroPaginas!!)
        txtAniosGarantia.setText(libro?.fechaPublicacion.toString())
    }

    fun crearProducto(){
        var numeroP = txtNumeroProd.text.toString().toInt()
        var nombreP = txtNombreProd.text.toString()
        var descripcion = txtDescripcionProd.text.toString()
        var precio = txtPrecioProd.text.toString()
        var fechaLanzamiento = txtFechaLanzamiento.text.toString()
        var Garantia = txtAniosGarantia.text.toString().toInt()
        var imagenLibro = myBase64Image

        if (!tipo){
            var libro = Libro(0, numeroP, nombreP, descripcion, precio, fechaLanzamiento, Garantia, imagenLibro, idAutor, 0, 0)
            DatabaseLibro.insertarLibro(libro)

        }else{
            var libro = Libro(libro?.id!!, numeroP, nombreP, descripcion, precio, fechaLanzamiento, Garantia, imagenLibro, idAutor, 0, 0)
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

            imageViewPokemon.setImageBitmap(myBitmapAgain)
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

