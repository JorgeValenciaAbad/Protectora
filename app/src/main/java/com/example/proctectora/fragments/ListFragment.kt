package com.example.proctectora.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import com.example.proctectora.R
import com.example.proctectora.dialogs.DeleteDialog
import com.example.proctectora.dialogs.InsertDialog
import com.example.proctectora.model.ArrayAdapterCustom
import com.example.proctectora.model.Pets
import com.example.proctectora.model.User
import com.example.proctectora.service.ShelterServiceSecurity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragment : Fragment, OnItemClickListener, View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var user: User
    private lateinit var pet : Pets
    private lateinit var pets : ArrayList<Pets>
    private lateinit var adpatador : ArrayAdapterCustom

    var listener:OnFragmentEventListener? = null

    constructor(user: User){
        this.user = user;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_list, container, false)
        var list : ListView = view.findViewById(R.id.listPets)
        var add : FloatingActionButton = view.findViewById(R.id.insert)
        var service = ShelterServiceSecurity(user)
        service.getPets().enqueue(object :  Callback<List<Pets>> {
            override fun onResponse(call: Call<List<Pets>>, response: Response<List<Pets>>) {
                if (response.isSuccessful){
                   pets = (response.body() as ArrayList<Pets>?)!!
                    context?.let {

                       adpatador= ArrayAdapterCustom(it.applicationContext,pets)

                    }
                    list.adapter = adpatador

                }
            }

            override fun onFailure(call: Call<List<Pets>>, t: Throwable) {
                Log.d("Error","Error")
            }

        })

        when(user.rol){
            1->{
                add.hide()


            }
            2->{
                add.setOnClickListener(this)

            }

        }
        list.onItemClickListener=this

        return view;
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        activity?.menuInflater?.inflate(R.menu.menu_contextual,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when(item?.itemId){
            R.id.delete->{

                var insert = DeleteDialog(user,pet)
                activity?.let {
                    insert?.show(it.supportFragmentManager,"insert")
                }
            }
        }
        return super.onContextItemSelected(item)
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
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
         pet  = parent?.getItemAtPosition(position) as Pets

        if (user.rol == 2){
            var insert = DeleteDialog(user,pet)
            activity?.let {
                insert?.show(it.supportFragmentManager,"insert")
            }
        }else{
            Log.d("Pet", pet.toString())
            Log.d("Pet2", user.toString())
            listener?.let {
                listener!!.PetsFragment(pet)
            }
        }


    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.insert){
            var insert = InsertDialog(user)
            activity?.let {
                insert?.show(it.supportFragmentManager,"insert")
            }
        }
    }

}