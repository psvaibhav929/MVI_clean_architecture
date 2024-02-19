package com.lloyd.data.remote

import com.lloyd.data.remote.dto.DogBreedDto
import com.lloyd.data.remote.dto.DogDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): DogBreedDto

    @GET("breed/{breed_name}/images/random")
    suspend fun getDogDetailsByBreedName(@Path("breed_name") dogBreedName : String): DogDetailsDto
}