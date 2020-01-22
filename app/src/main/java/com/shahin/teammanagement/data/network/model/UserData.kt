package com.shahin.teammanagement.data.network.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 */
class UserData : Parcelable {

    @Expose
    @SerializedName("last")
    var description: String? = null

    @Expose
    @SerializedName("thumbnail")
    var image: String? = null

    @Expose
    @SerializedName("gender")
    var gender: String? = null


    protected constructor(`in`: Parcel) {
        description = `in`.readString()
        image = `in`.readString()
        gender = `in`.readString()
    }

    constructor(description: String, image: String, gender: String) {
        this.description = description
        this.image = image
        this.gender = gender
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(gender)
    }



    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
