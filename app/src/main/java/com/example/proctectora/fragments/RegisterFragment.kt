package com.example.proctectora.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.proctectora.R
import com.example.proctectora.SecondActivity
import com.example.proctectora.model.User
import com.example.proctectora.service.ShelterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment(), View.OnClickListener {
    var service = ShelterService()
    private lateinit var error : TextView
    private lateinit var user : EditText
    private lateinit var pass : EditText
    private lateinit var register : Button
    private var listener : onFragmentListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        user = view.findViewById(R.id.user)
        pass = view.findViewById(R.id.pass)
        register = view.findViewById(R.id.register)
        error = view.findViewById(R.id.error)

        register.setOnClickListener(this)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onFragmentListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onClick(v: View?) {
        if (user.text.isNotEmpty() && pass.text.isNotEmpty() ){
            if(v?.id == R.id.register) {
                service.addUser(User(user.text.toString(), pass.text.toString(), 1)).enqueue(object :
                    Callback<User> {

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        error.text = "This username is already registered"
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {

                            listener?.let {
                                listener!!.toLogIn()
                            }
                            Log.d("TAG", "Usuario Creado")
                        } else {
                            error.text = "This username is already registered"
                        }
                    }
                })
            }
        }
    }
}


