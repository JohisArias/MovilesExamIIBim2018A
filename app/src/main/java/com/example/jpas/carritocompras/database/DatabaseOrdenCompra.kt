package com.example.andreavillacis.av_exammoviles_iib.database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenDetalles
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class DatabaseOrdenCompra{
    companion object {
        fun insertarOrden(ordenCompra: OrdenCompra){
            "http://192.168.0.106:1337/Orden".httpPost(listOf("cedulaComprador" to ordenCompra.cedulaComprador, "sector" to ordenCompra.sector, "idLibro" to ordenCompra.idLibro))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun insertarOrdenDetalles(ordenDetalles: OrdenDetalles){
            "http://192.168.0.106:1337/DetalleOrden".httpPost(listOf("fechaEnvio" to ordenDetalles.fechaEnvio, "precio" to ordenDetalles.precio, "idLibro" to ordenDetalles.idLibro))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getOrdenesList(): ArrayList<OrdenCompra> {
            val orden: ArrayList<OrdenCompra> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.106:1337/Orden".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val cedulaIdentidad = it["cedulaComprador"] as Int
                val sector = it["sector"] as String
                val idLibro = it["idLibro"] as Int
                //val latitud = it["latitud"] as Double
                // val longitud = it["longitud"] as Double
                val ordenn = OrdenCompra(0, cedulaIdentidad, sector, idLibro)
                orden.add(ordenn)
            }
            return orden
        }
    }
}