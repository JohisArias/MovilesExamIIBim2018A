package com.example.andreavillacis.av_exammoviles_iib.database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import java.util.*

class DatabaseLibro{
    companion object {

        fun insertarLibro(libro: Libro){
            "http://192.168.0.106:1337/Libro".httpPost(listOf("isbn" to libro.isbn, "nombre" to libro.nombre, "nombreEditorial" to libro.nombreEditorial, "precio" to libro.precio, "numeroPaginas" to libro.numeroPaginas,"fechaPublicacion" to libro.fechaPublicacion,"imagenLibro" to libro.imagenLibro,"autorId" to libro.autorId ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarLibro(id: Int) {
            "http://192.168.0.106:1337/Libro/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizaLibro(libro: Libro) {
            "http://192.168.0.106:1337/Libro/${libro.id}".httpPut(listOf("isbn" to libro.isbn, "nombre" to libro.nombre, "nombreEditorial" to libro.nombreEditorial, "precio" to libro.precio, "numeroPaginas" to libro.numeroPaginas, "fechaPublicacion" to libro.fechaPublicacion))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getLibroList(autorId: Int): ArrayList<Libro> {
            val libro: ArrayList<Libro> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.106:1337/Libro?autorId=$autorId".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val isbn = it["isbn"] as Int
                val nombre = it["nombre"] as String
                val editorial = it["nombreEditorial"] as String
                val precio = it["precio"] as String
                val numeroPaginas = it["numeroPaginas"] as Int
                val fechaPublicacion = it["fechaPublicacion"] as String
                val imagenLibro = it["imagenLibro"] as String
                //val latitud = it["latitud"] as Double
               // val longitud = it["longitud"] as Double
                val libros = Libro(id, isbn, nombre, editorial, precio, numeroPaginas, fechaPublicacion, imagenLibro, autorId, 0, 0)
                libro.add(libros)
            }
            return libro
        }//fin getLibroList
    }//fin companion object
}//fin DatabaseLibro