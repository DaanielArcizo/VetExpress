package com.ebc.vetexpress.data.api

import com.ebc.vetexpress.data.model.DogImage
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDog(): DogImage
}
