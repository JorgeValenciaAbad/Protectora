package com.example.proctectora.model

import java.io.Serializable

class User: Serializable {

    var user_id : Int;
    var name:String;
    var pass:String;
    var rol:Int;
    var token:String;

    constructor(id : Int, name:String, pass:String, rol:Int, token:String){
        this.user_id = id;
        this.name = name;
        this.pass = pass
        this.rol = rol;
        this.token = token;
    }
    constructor(name:String, pass:String, rol:Int){
        this.user_id = 0;
        this.name = name;
        this.pass = pass
        this.rol = rol;
        this.token = "";
    }

    override fun toString(): String {
        return "User(id=$user_id, name='$name', pass='$pass', rol=$rol, token='$token')"
    }

}