package com.example.memes.model

import com.example.memes.utlis.Resource

interface MemeApi {

    suspend fun getMemes(count:Int):Resource<List<Meme>>
}