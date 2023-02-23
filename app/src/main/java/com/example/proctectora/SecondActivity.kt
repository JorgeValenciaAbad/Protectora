package com.example.proctectora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.identity.AccessControlProfile
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import com.example.proctectora.fragments.*
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User

class SecondActivity : AppCompatActivity(), OnFragmentEventListener, OnClickListener{
    private lateinit var user: User
    private lateinit var profile: TextView
    private lateinit var pets : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        profile = findViewById(R.id.profile)
        pets = findViewById(R.id.pets)
        profile.setOnClickListener(this)
        pets.setOnClickListener(this)
        if(intent.hasExtra("user")){

            user = intent.getSerializableExtra("user") as User


        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.profile->{
                val frag = ProfileFragment(user)
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.fl,frag)
                ft.commit()
            }
            R.id.pets->{
                toList()
            }

        }
    }

    override fun PetsFragment(pet:Pets) {
        Log.d("Pet3",user.user_id.toString())
        val frag = AdoptionFragment(pet, user)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fl,frag)
        ft.commit()
    }

    override fun toList() {
        val frag = ListFragment(user)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fl,frag)
        ft.commit()
    }


}