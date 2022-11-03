package co.edu.uniandes.app.movil202215.view

import android.os.Parcel
import android.os.Parcelable

data class AlbumModel(
    var id: Int,
    var name: String,
    var cover: String,
    var releaseDate: String,
    var description: String,
    var genre: String,
    var recordLabel: String,
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(cover)
        parcel.writeString(releaseDate)
        parcel.writeString(description)
        parcel.writeString(genre)
        parcel.writeString(recordLabel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumModel> {
        override fun createFromParcel(parcel: Parcel): AlbumModel {
            return AlbumModel(parcel)
        }

        override fun newArray(size: Int): Array<AlbumModel?> {
            return arrayOfNulls(size)
        }
    }
}
