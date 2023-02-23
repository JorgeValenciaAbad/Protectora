package com.example.proctectora.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.proctectora.R
import com.example.proctectora.model.Adoption
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User
import com.example.proctectora.service.ShelterServiceSecurity
import retrofit2.Call
import retrofit2.Response


class AdoptionFragment(private var pet: Pets, private var user: User) : Fragment(), View.OnClickListener {

    var listener : OnFragmentEventListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_adoption, container, false)
        val name:TextView = view.findViewById(R.id.name)
        val age:TextView = view.findViewById(R.id.age)
        val description:TextView = view.findViewById(R.id.description)
        val category:TextView = view.findViewById(R.id.category)
        val image:ImageView = view.findViewById(R.id.image)
        val cancel :Button = view.findViewById(R.id.back)
        val adoption :Button = view.findViewById(R.id.adoption)
        cancel.setOnClickListener(this)
        adoption.setOnClickListener(this)
        name.text = "Name: "+pet.name
        age.text = "Age: "+pet.age.toString()
        description.text = "Description: "+pet.description
        category.text = "Category: "+pet.type
        when(pet.type){
            "perro"->{
                image.setImageResource(R.drawable.perro)
            }
            "gato"->{
                image.setImageResource(R.drawable.gato)
            }
            "pajaro"->{
                image.setImageResource(R.drawable.pajaro)
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentEventListener){
            listener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.adoption->{
                 var service = ShelterServiceSecurity(user)
                 service.adoption(Adoption(user.user_id,pet.pet_id)).enqueue(object : retrofit2.Callback<Adoption> {
                     override fun onResponse(call: Call<Adoption>, response: Response<Adoption>) {
                         if (response.isSuccessful){
                             Log.d("Adoption", response.body().toString())
                             listener.let {
                                 listener!!.toList()
                             }

                         }

                     }

                     override fun onFailure(call: Call<Adoption>, t: Throwable) {
                         Log.d("error", t.toString())
                     }


                 } )


            }
            R.id.back->{
                listener.let {
                    listener!!.toList()
                }
            }
        }

    }

}