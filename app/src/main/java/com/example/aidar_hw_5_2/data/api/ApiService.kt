package com.example.aidar_hw_5_2.data.api

import com.example.aidar_hw_5_2.data.model.characters.BaseResponse
import com.example.aidar_hw_5_2.data.model.characters.Character
import com.example.aidar_hw_5_2.data.model.detail.BaseDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
    ): Response<BaseResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): Response<Character>
}