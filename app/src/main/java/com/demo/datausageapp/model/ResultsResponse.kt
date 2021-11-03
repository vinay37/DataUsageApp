package com.demo.datausageapp.model

import com.demo.datausageapp.model.Record
import com.google.gson.annotations.SerializedName

data class ResultsResponse(
    @SerializedName("records")      val records     : ArrayList<Record>
)
