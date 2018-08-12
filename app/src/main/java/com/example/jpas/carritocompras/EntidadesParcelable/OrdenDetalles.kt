package com.example.jpas.carritocompras.EntidadesParcelable

import android.os.Parcel
import android.os.Parcelable

class OrdenDetalles(var id:Int,var fechaEnvio:String,var precio:Int,var idLibro:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(fechaEnvio)
        parcel.writeInt(precio)
        parcel.writeInt(idLibro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrdenDetalles> {
        override fun createFromParcel(parcel: Parcel): OrdenDetalles {
            return OrdenDetalles(parcel)
        }

        override fun newArray(size: Int): Array<OrdenDetalles?> {
            return arrayOfNulls(size)
        }
    }
}