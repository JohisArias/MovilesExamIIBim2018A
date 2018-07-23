package com.example.andreavillacis.av_exammoviles_iib.Auto

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.andreavillacis.av_exammoviles_iib.Detalle.DetallesLibroClienteActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Libro
import com.example.jpas.carritocompras.R

class LibroClienteAdapter(private val libroList: List<Libro>) :  RecyclerView.Adapter<LibroClienteAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var descripcion : TextView
        var precio: TextView
        var detalles: Button

        lateinit var libro: Libro

        init {
            nombre = view.findViewById(R.id.txtNombreTienda) as TextView
            descripcion = view.findViewById(R.id.txtDireccionTienda) as TextView
            precio = view.findViewById(R.id.txtFechaAperturaTienda) as TextView
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
        val productoN = libroList[position]
        holder.nombre.text = productoN.nombre
        holder.descripcion.text = productoN.nombreEditorial
        holder.precio.text = productoN.precio
        holder.libro = productoN
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesLibroClienteActivity::class.java)
            intent.putExtra("detallesLibroCliente", productoN)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return libroList.size
    }


}