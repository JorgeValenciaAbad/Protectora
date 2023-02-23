package com.example.proctectora.service

import com.example.proctectora.dao.ShelterDAO
import com.example.proctectora.model.Adoption
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShelterServiceSecurity (user:User){

    val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization",user.token.toString())
            .build()
        chain.proceed(request)
    }.build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.31.17:5002/api/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getPets(): Call<List<Pets>> {
        return getRetrofit().create(ShelterDAO::class.java).getPets()
    }
    fun adoption(adoption: Adoption): Call<Adoption>{
        return getRetrofit().create(ShelterDAO::class.java).adoption(adoption)
    }
    fun addPet(pet:Pets):Call<Pets>{
        return getRetrofit().create(ShelterDAO::class.java).addPet(pet)
    }
    fun updadePet(pet:Pets):Call<Pets>{
        return getRetrofit().create(ShelterDAO::class.java).updatePet(pet)
    }
    fun deletePet(id: Int):Call<Pets>{
        return getRetrofit().create(ShelterDAO::class.java).deletePet(id)
    }
}