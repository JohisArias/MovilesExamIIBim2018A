package com.example.andreavillacis.av_exammoviles_iib.Conductor

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.andreavillacis.av_exammoviles_iib.Detalle.DetallesAutorClienteActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.jpas.carritocompras.R

class AutorClienteAdapter(private val autorList: List<Autor>) :  RecyclerView.Adapter<AutorClienteAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var direccion : TextView
        var fechaApertura: TextView
        var detalles: Button

        lateinit var autor: Autor

        init {
            nombre = view.findViewById(R.id.txtNombreTienda) as TextView
            direccion = view.findViewById(R.id.txtDireccionTienda) as TextView
            fechaApertura = view.findViewById(R.id.txtFechaAperturaTienda) as TextView
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
        val autor = autorList[position]
        holder.nombre.text = autor.nombre
        holder.direccion.text = autor.apellido
        holder.fechaApertura.text = autor.fechaNacimiento
        holder.autor = autor
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesAutorClienteActivity::class.java)
            intent.putExtra("detallesAutorCliente", autor)

            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return autorList.size
    }
}