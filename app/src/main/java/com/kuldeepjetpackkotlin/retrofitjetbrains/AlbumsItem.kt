package com.kuldeepjetpackkotlin.retrofitjetbrains


import com.google.gson.annotations.SerializedName

//step 2

data class AlbumsItem(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("userId")
    val userId: Int
)