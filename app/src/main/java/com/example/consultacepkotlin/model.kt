package com.example.consultacepkotlin

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("userID")
    var userID : Int,
    @SerializedName("id")
    var id : Int,
    @SerializedName("title")
    var title : String,
    @SerializedName("body")
    var body : String
)