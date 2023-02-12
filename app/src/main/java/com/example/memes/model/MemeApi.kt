package com.example.memes.model

import com.example.memes.utlis.Resource

interface MemeApi {

    suspend fun getMemes():Resource<List<Meme>>
}