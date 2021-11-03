package com.demo.datausageapp.model

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("_id")                      val id      : Int,
    @SerializedName("quarter")                  val quarter : String,
    @SerializedName("volume_of_mobile_data")    val volume  : Float
)