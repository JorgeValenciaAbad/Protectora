package com.example.proctectora.dao

import com.example.proctectora.model.Adoption
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User
import retrofit2.Call
import retrofit2.http.*

interface ShelterDAO {
    @FormUrlEncoded
    @POST("users/login")
    fun login (@Field("user") name:String, @Field("pass") pass:String):Call<User>;
    @POST("users")
    fun addUser(@Body user: User):Call<User>
    @GET("pets/adoption")
    fun getPets():Call<List<Pets>>
    @POST("adoption")
    fun adoption(@Body adoption: Adoption):Call<Adoption>
    @POST("pets")
    fun addPet (@Body pet: Pets):Call<Pets>
    @PUT("pets")
    fun updatePet (@Body pet: Pets):Call<Pets>
    @DELETE("pets/{id}")
    fun deletePet(@Path("id") id : Int):Call<Pets>

}