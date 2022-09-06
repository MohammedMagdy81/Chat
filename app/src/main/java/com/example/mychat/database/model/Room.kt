package com.example.mychat.database.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Room(var id:String?=null,var name:String?=null,var desc:String?=null):Parcelable