package com.example.jpas.carritocompras.EntidadesParcelable

import android.os.Parcel
import android.os.Parcelable

class Libro(var id: Int,
            var isbn: Int,
            var nombre: String,
            var nombreEditorial: String,
            var precio: String,
            var numeroPaginas: Int,
            var fechaPublicacion: String,
            var imagenLibro:String,
            var autorId:Int,
            var createdAt: Long,
            var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(isbn)
        parcel.writeString(nombre)
        parcel.writeString(nombreEditorial)
        parcel.writeString(precio)
        parcel.writeInt(numeroPaginas)
        parcel.writeString(fechaPublicacion)
        parcel.writeString(imagenLibro)
        parcel.writeInt(autorId)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}
