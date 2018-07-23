package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseOrdenCompra
import com.example.jpas.carritocompras.R
import kotlinx.android.synthetic.main.activity_delivery.*

class DeliveryActivity : AppCompatActivity() {

    lateinit var adaptador: OrdenesPendientesAdaptador
    lateinit var ordenes: ArrayList<OrdenCompra>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        ordenes = DatabaseOrdenCompra.getOrdenesList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = OrdenesPendientesAdaptador(ordenes)
        recyclerView_ordenes.layoutManager = layoutManager
        recyclerView_ordenes.itemAnimator = DefaultItemAnimator()
        recyclerView_ordenes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_ordenes)
    }
}
