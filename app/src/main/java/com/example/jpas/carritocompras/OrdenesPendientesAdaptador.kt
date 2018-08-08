package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.andreavillacis.av_exammoviles_iib.Detalle.DetalleOrdenActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.jpas.carritocompras.R

class OrdenesPendientesAdaptador(private val ordenesList: List<OrdenCompra>) :  RecyclerView.Adapter<OrdenesPendientesAdaptador.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var cedula: TextView
        var sector : TextView
        var idLibro: TextView
        var detalles: Button

        lateinit var ordenn: OrdenCompra

        init {
            cedula = view.findViewById(R.id.txtNombreAutor) as TextView
            sector = view.findViewById(R.id.txtApellido) as TextView
            idLibro = view.findViewById(R.id.txtFechaNacimiento) as TextView
            detalles = view.findViewById(R.id.btnDetalles) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_autor_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val orden = ordenesList[position]
        holder.cedula.text = orden.cedulaComprador.toString()
        holder.sector.text = orden.sector
        holder.idLibro.text = orden.idLibro.toString()
        holder.ordenn = orden
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetalleOrdenActivity::class.java)
            intent.putExtra("detallesOrden", orden)

            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return ordenesList.size
    }
}