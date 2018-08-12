package com.example.jpas.carritocompras.Database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.*
import com.example.jpas.carritocompras.EntidadesParcelable.Usuario
import com.github.kittinunf.fuel.*

class DatabaseUsuario{
    companion object {

        fun insertarUsuario(usuario: Usuario) {
            "http://192.168.0.106:1337/Usuario".httpPost(listOf("rol" to usuario.rol, "username" to usuario.username, "password" to usuario.password))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Usuario> {
            val usuarios: ArrayList<Usuario> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.106:1337/Usuario".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val rol = it["rol"] as String
                val nombreUsuario = it["username"] as String
                val password = it["password"] as String
                val usuario = Usuario(rol, nombreUsuario, password, 0, 0)
                usuarios.add(usuario)
            }
            return usuarios
        }
    }
}
