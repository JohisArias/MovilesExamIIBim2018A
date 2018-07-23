package com.example.andreavillacis.av_exammoviles_iib.database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.github.kittinunf.fuel.*

class DatabaseAutor{

    companion object {

        fun insertarAutor(autor: Autor){
            "http://192.168.0.106:1337/Autor".httpPost(listOf("nombre" to autor.nombre, "apellido" to autor.apellido, "fechaNacimiento" to autor.fechaNacimiento, "numeroLibros" to autor.numeroLibros, "ecuatoriano" to autor.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarAutor(id: Int) {
            "http://192.168.0.106:1337/Autor/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizaAutor(autor: Autor) {
            "http://192.168.0.106:1337/Autor/${autor.id}".httpPut(listOf("nombre" to conductor.nombre, "apellido" to conductor.apellido, "fechaNacimiento" to conductor.fechaNacimiento, "numeroLibros" to conductor.numeroLibros, "ecuatoriano" to conductor.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Autor> {
            val autor: ArrayList<Autor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.106:1337/Autor".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellio = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroLibros = it["numeroLibros"] as Int
                val ecuatoriano = it["ecuatoriano"] as Int
                val conductores = Autor(id, nombre, apellio, fechaNacimiento, numeroLibros, ecuatoriano, 0, 0)
                autor.add(conductores)
            }
            return autor
        }

        fun buscarAutor(nombre:String): ArrayList<Autor> {
            val autor: ArrayList<Autor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.106:1337/Autor?nombre=${nombre}".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it[ "fechaNacimiento"] as String
                val numeroLibros= it["numeroLibros"] as Int
                val ecuatoriano = it["ecuatoriano"] as Int
                val autores = Autor(id, nombre, apellido, fechaNacimiento, numeroLibros, ecuatoriano, 0, 0)
                autor.add(autores)
            }
            return autor
        }
    }
}