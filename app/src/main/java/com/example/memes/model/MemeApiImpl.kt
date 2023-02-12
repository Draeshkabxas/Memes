package com.example.memes.model

import android.util.Log
import com.example.memes.utlis.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*


class MemeApiImpl(private val client: HttpClient):MemeApi{
    override suspend fun getMemes(): Resource<List<Meme>> {
        return try {
            val response:MemeResponse = client.get {
                url("https://meme-api.com/gimme/10")
            }.body()
            Resource.Success(response.memes)
        } catch (e: RedirectResponseException) {
            Log.e("MemeApi", "3XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: ClientRequestException) {
            Log.e("MemeApi", "4XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: ServerResponseException) {
            Log.e("MemeApi", "5XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: Exception) {
            Log.e("MemeApi", "Error: ${e.message}")
            Resource.Error("${e.message}")
        }
    }
}