package com.example.proctectora.model

import java.io.Serializable

class Adoption : Serializable {
    var id : Int
    var user_id: Int
    var pet_id: Int
    constructor( id : Int,  id_user: Int,  id_pet: Int){
        this.id = id
        this.user_id = id_user
        this.pet_id = id_pet
    }
    constructor( id_user: Int,  id_pet: Int){
        this.id = 0
        this.user_id = id_user
        this.pet_id = id_pet
    }
    override fun toString(): String {
        return "Adoption(id=$id, id_user=$user_id, id_pet=$pet_id)"
    }
}