package com.example.jpas.carritocompras

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.jpas.carritocompras.Detalle.DetallesLibroClienteActivity
import com.example.jpas.carritocompras.EntidadesParcelable.Libro

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
        var editorial : TextView
        var precio: TextView
        var detalles: Button

        lateinit var libro: Libro

        init {
            nombre = view.findViewById(R.id.txtNombreAutor) as TextView
            editorial = view.findViewById(R.id.txtApellido) as TextView
            precio = view.findViewById(R.id.txtFechaNacimiento) as TextView
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
        val libro = libroList[position]
        holder.nombre.text = libro.nombre
        holder.editorial.text = libro.nombreEditorial
        holder.precio.text = libro.precio
        holder.libro = libro
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesLibroClienteActivity::class.java)
            intent.putExtra("detallesLibroCliente", libro)
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