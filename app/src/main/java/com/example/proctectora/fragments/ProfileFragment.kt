package com.example.proctectora.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.proctectora.R
import com.example.proctectora.model.User


class ProfileFragment : Fragment {

    private lateinit var user: User
    constructor(user: User){
        this.user = user
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)
        var email: TextView = view.findViewById(R.id.email)
        var rol: TextView = view.findViewById(R.id.pass)
        email.text = "Email: "+user.name
        when(user.rol){
            1->{
                rol.text="Cliente"
            }
            2->{
                rol.text="Administrador"
            }
        }
        return view
    }

}