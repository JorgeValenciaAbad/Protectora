package com.example.proctectora.model

import java.io.Serializable

class Pets: Serializable {
    var pet_id :Int
    var name : String
    var type :String
    var age: Int
    var description: String
    var reserva:Boolean

    constructor(
        pet_id: Int,
        name: String,
        type: String,
        age: Int,
        description: String,
        reserva: Boolean
    ) {
        this.pet_id = pet_id
        this.name = name
        this.type = type
        this.age = age
        this.description = description
        this.reserva = reserva
    }
    constructor(
        name: String,
        type: String,
        age: Int,
        description: String,
        reserva: Boolean
    ) {
        this.pet_id = 0
        this.name = name
        this.type = type
        this.age = age
        this.description = description
        this.reserva = reserva
    }
    override fun toString(): String {
        return "Pets(id=$pet_id, name='$name', type='$type', age=$age, sumary='$description', reserva=$reserva)"
    }
}