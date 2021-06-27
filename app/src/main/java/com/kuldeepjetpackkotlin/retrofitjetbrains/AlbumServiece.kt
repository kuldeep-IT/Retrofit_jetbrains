package com.kuldeepjetpackkotlin.retrofitjetbrains

import retrofit2.Response
import retrofit2.http.*


//step 3
interface AlbumServiece {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbum(@Query("userId") userId: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getPathAlbum(@Path("id") id: Int) : Response<AlbumsItem>

    @POST("/albums")
    suspend fun postData(@Body albumsItem: AlbumsItem)  : Response<AlbumsItem>

}