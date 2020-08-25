package com.primateknos.perlindungankonsumen.listMateri

import android.os.Parcel
import android.os.Parcelable

class ExpandListModel : Parcelable {
    var bab : String?=null;
    var nama: String?=null;
    var file: String?=null;



    constructor() {}
    protected constructor(`in`: Parcel) {
        bab = `in`.readString()
        nama = `in`.readString()
        file = `in`.readString()
    }

    constructor(bab: String?, nama: String?, file: String?) {
        this.bab = bab
        this.nama = nama
        this.file = file
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(bab)
        dest.writeString(nama)
        dest.writeString(file)
    }

    companion object CREATOR : Parcelable.Creator<ExpandListModel> {
        override fun createFromParcel(parcel: Parcel): ExpandListModel {
            return ExpandListModel(parcel)
        }

        override fun newArray(size: Int): Array<ExpandListModel?> {
            return arrayOfNulls(size)
        }
    }
}
