package com.example.proctectora.dialogs

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.proctectora.R
import com.example.proctectora.fragments.OnFragmentEventListener
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User
import com.example.proctectora.service.ShelterServiceSecurity
import retrofit2.Call
import retrofit2.Response

class DeleteDialog( var user: User,var pets: Pets):DialogFragment(), View.OnClickListener {

    private  var listener : OnFragmentEventListener? = null
    private lateinit var name :TextView
    private lateinit var cancel : Button
    private lateinit var delete : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView : View = inflater.inflate(R.layout.delete_dialog,container,false)
        name = rootView.findViewById(R.id.name)
        delete = rootView.findViewById(R.id.delete)
        cancel = rootView.findViewById(R.id.cancel)
        delete.setOnClickListener(this)
        cancel.setOnClickListener(this)
        name.text = "¿Seguro que quieres eliminar a "+pets.name+"?"


        return rootView
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cancel->{
                dismiss()
            }
            R.id.delete->{
                var service = ShelterServiceSecurity(user)
                service.deletePet(pets.pet_id).enqueue(object :
                    retrofit2.Callback<Pets> {
                    override fun onResponse(call: Call<Pets>, response: Response<Pets>) {
                        if (response.isSuccessful){
                            Log.d("PetAdd",response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<Pets>, t: Throwable) {
                        Log.d("PetAdd","error")
                    }

                })
                dismiss()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentEventListener){
            listener = getContext() as OnFragmentEventListener?
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.let {
            listener!!.toList()
        }
    }

}