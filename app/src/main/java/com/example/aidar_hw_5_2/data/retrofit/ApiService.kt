package com.example.aidar_hw_5_2.data.retrofit

import com.example.aidar_hw_5_2.data.model.characters.BaseResponse
import com.example.aidar_hw_5_2.data.model.detail.BaseDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<BaseResponse>

    @GET("id")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): Response<BaseDetailResponse>
}