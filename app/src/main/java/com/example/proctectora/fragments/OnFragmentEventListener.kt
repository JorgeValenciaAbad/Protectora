package com.example.proctectora.fragments

import com.example.proctectora.model.Pets

interface OnFragmentEventListener {
    fun PetsFragment(pet: Pets)
    fun toList()
}