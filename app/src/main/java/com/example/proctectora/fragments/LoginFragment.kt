package com.example.proctectora.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.proctectora.R
import com.example.proctectora.SecondActivity
import com.example.proctectora.service.ShelterService
import com.example.proctectora.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment(),OnClickListener {

    private lateinit var client : User
    private lateinit var error : TextView
    private lateinit var user : EditText
    private lateinit var pass : EditText
    private lateinit var login : Button
    var service = ShelterService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        user = view.findViewById(R.id.user)
        pass = view.findViewById(R.id.pass)
        login = view.findViewById(R.id.login)
        error = view.findViewById(R.id.error)
        login.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.login){
            service.login(user.text.toString(),pass.text.toString()).enqueue(object: Callback<User>{

                override fun onFailure(call: Call<User>, t: Throwable) {
                    error.text = "Email or password are incorrect"
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        client  = response.body()!!
                        val intent = Intent(context, SecondActivity::class.java).apply {
                            putExtra("user", client)
                        }
                        Log.d("TAG","Usuario Logeado")
                        startActivity(intent)
                    }else{
                       error.text = "Email or password are incorrect"
                    }
                }
            })
        }
    }
}