package com.example.andreavillacis.av_exammoviles_iib.Conductor

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.andreavillacis.av_exammoviles_iib.Detalle.DetallesAutorActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Autor
import com.example.jpas.carritocompras.R

class AutorAdapter(private val autorList: List<Autor>) :  RecyclerView.Adapter<AutorAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombres: TextView
        var direccion : TextView
        var fechaApertura: TextView
        var detalles: Button

        lateinit var autor: Autor

        init {
            nombres = view.findViewById(R.id.txtNombreTienda) as TextView
            direccion = view.findViewById(R.id.txtDireccionTienda) as TextView
            fechaApertura = view.findViewById(R.id.txtFechaAperturaTienda) as TextView
            detalles = view.findViewById(R.id.btnDetalles) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_autor_layout, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val autor = autorList[position]
        holder.nombres.text = autor.nombre
        holder.direccion.text = autor.apellido
        holder.fechaApertura.text = autor.fechaNacimiento
        holder.autor = autor
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesAutorActivity::class.java)
            intent.putExtra("detallesAutor", autor)
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