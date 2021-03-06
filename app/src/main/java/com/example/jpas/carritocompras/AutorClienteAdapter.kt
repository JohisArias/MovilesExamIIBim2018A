package com.example.jpas.carritocompras

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.jpas.carritocompras.Detalle.DetallesAutorClienteActivity
import com.example.jpas.carritocompras.EntidadesParcelable.Autor

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
        var apellido : TextView
        var fechaNacimiento: TextView
        var detalles: Button

        lateinit var autor: Autor

        init {
            nombre = view.findViewById(R.id.txtNombreAutor) as TextView
            apellido = view.findViewById(R.id.txtApellido) as TextView
            fechaNacimiento = view.findViewById(R.id.txtFechaNacimiento) as TextView
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
        holder.apellido.text = autor.apellido
        holder.fechaNacimiento.text = autor.fechaNacimiento
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