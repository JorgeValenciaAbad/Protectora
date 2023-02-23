package com.example.proctectora.dialogs

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.proctectora.R
import com.example.proctectora.fragments.OnFragmentEventListener
import com.example.proctectora.fragments.onFragmentListener
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User
import com.example.proctectora.service.ShelterServiceSecurity
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class InsertDialog( var user : User) :DialogFragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var name : EditText
    private lateinit var age : EditText
    private lateinit var category : Spinner
    private lateinit var description : TextView
    private lateinit var cancel : Button
    private lateinit var add : Button
    var listener : OnFragmentEventListener? = null
    var type : String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView : View = inflater.inflate(R.layout.dialog_insert,container,false)
        name = rootView.findViewById(R.id.name)
        age = rootView.findViewById(R.id.age)
        category = rootView.findViewById(R.id.category)
        description = rootView.findViewById(R.id.description)
        add = rootView.findViewById(R.id.addPet)
        cancel = rootView.findViewById(R.id.cancel)
        category.adapter = context?.let { ArrayAdapter.createFromResource(it, R.array.type, android.R.layout.simple_list_item_1) }
        add.setOnClickListener(this)
        cancel.setOnClickListener(this)
        category.onItemSelectedListener = this

        return rootView
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addPet->{
                var  service  =  ShelterServiceSecurity(user)

                service.addPet(Pets(name.text.toString(),type,age.text.toString().toInt(),description.text.toString(),false)).enqueue(object :
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
           R.id.cancel->{
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


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.category ->{
                type = parent?.adapter?.getItem(position).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}