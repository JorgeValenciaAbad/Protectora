package com.example.proctectora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.proctectora.fragments.LoginFragment
import com.example.proctectora.fragments.RegisterFragment
import com.example.proctectora.fragments.onFragmentListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButton.OnCheckedChangeListener

class MainActivity : AppCompatActivity() ,onFragmentListener{
    private lateinit var sw : Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toLogIn()
        sw = findViewById(R.id.sw)
        sw.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked) {
                val frag = RegisterFragment()
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.fl,frag)
                ft.commit()
            } else {
                val frag = LoginFragment()
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.fl,frag)
                ft.commit()

            }

        }

    }


    override fun toLogIn() {

        val frag = LoginFragment()
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fl,frag)
        ft.commit()
    }
}