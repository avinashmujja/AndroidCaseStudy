package com.android.ghcasestudy.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class  GitUser constructor(@SerializedName("login")
                                   @Expose
                                   val login : String,
                               @SerializedName("id")
                                   @Expose
                                   val id : Int?,
                               @SerializedName("node_id")
                                   @Expose
                                   val node_id : String?,
                               @SerializedName("avatar_url")
                                   @Expose
                                   val avatar_url : String?,
                               @SerializedName("url")
                                   @Expose
                                   val url : String?,
                               @SerializedName("name")
                                   @Expose
                                   val name : String?,
                               @SerializedName("company")
                                   @Expose
                                   val company : String?,
                               @SerializedName("blog")
                                   @Expose
                                   val blog : String?,
                               @SerializedName("location")
                                   @Expose
                                   val location : String?,
                               @SerializedName("email")
                                   @Expose
                                   val email : String?,
                               @SerializedName("bio")
                                   @Expose
                                   val bio : String?,
                               @SerializedName("followers")
                               @Expose
                               val followers : Int? = 0,
                               @SerializedName("following")
                               @Expose
                               val following : Int? = 0):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeValue(id)
        parcel.writeString(node_id)
        parcel.writeString(avatar_url)
        parcel.writeString(url)
        parcel.writeString(name)
        parcel.writeString(company)
        parcel.writeString(blog)
        parcel.writeString(location)
        parcel.writeString(email)
        parcel.writeString(bio)
        parcel.writeValue(followers)
        parcel.writeValue(following)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitUser> {
        override fun createFromParcel(parcel: Parcel): GitUser {
            return GitUser(parcel)
        }

        override fun newArray(size: Int): Array<GitUser?> {
            return arrayOfNulls(size)
        }
    }
}