package com.example.proctectora.service

import com.example.proctectora.dao.ShelterDAO
import com.example.proctectora.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShelterService {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.31.17:5002/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun login(user:String, pass:String ):Call<User>{
        return getRetrofit().create(ShelterDAO::class.java).login(user,pass)
    }
    fun addUser(user: User ):Call<User>{
        return getRetrofit().create(ShelterDAO::class.java).addUser(user)
    }
}