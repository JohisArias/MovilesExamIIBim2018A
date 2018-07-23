package com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable

import android.os.Parcel
import android.os.Parcelable

class OrdenCompra(var idCompra:Int,
                  var cedulaComprador:Int,
                  var sector:String,
                  var idLibro:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idCompra)
        parcel.writeInt(cedulaComprador)
        parcel.writeString(sector)
        parcel.writeInt(idLibro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrdenCompra> {
        override fun createFromParcel(parcel: Parcel): OrdenCompra {
            return OrdenCompra(parcel)
        }

        override fun newArray(size: Int): Array<OrdenCompra?> {
            return arrayOfNulls(size)
        }
    }
}