package com.practice.retrofitpractice

import retrofit2.Response
import retrofit2.http.*

interface AlbumServices {

    @GET(value = "/albums")
    suspend fun getAlbums() : Response<Albums>

    @GET(value = "/albums")
    suspend fun getSortedAlbums(@Query(value = "userId") userId : Int) : Response<Albums>

    @GET(value = "/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") id : Int) : Response<AlbumsItem>

    @POST(value = "/albums")
    suspend fun postAlbum(@Body albums: AlbumsItem) :Response<AlbumsItem>

}