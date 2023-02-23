package com.example.proctectora.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.proctectora.R
import com.example.proctectora.fragments.ListFragment
import retrofit2.Callback

class ArrayAdapterCustom:ArrayAdapter<Pets> {

    private var list : ArrayList<Pets>

    constructor(context: Context, array: ArrayList<Pets>):super(context, R.layout.item, array){
        this.list = array
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(context)
        val convertView = inflater.inflate(R.layout.item, null)
        var nombre = convertView.findViewById<TextView>(R.id.nombre)
        var peso = convertView.findViewById<TextView>(R.id.peso)
        var imagen  = convertView.findViewById<ImageView>(R.id.imagen)
        nombre.text = list[position].name
        peso.text = list[position].age.toString()+ "años"
        if (list[position].type  == "perro"){
            imagen.setImageResource(R.drawable.perro)
        }else if (list[position].type  == "pajaro"){
            imagen.setImageResource(R.drawable.pajaro)
        }else if (list[position].type  == "gato"){
            imagen.setImageResource(R.drawable.gato)
        }
        return convertView
    }

}