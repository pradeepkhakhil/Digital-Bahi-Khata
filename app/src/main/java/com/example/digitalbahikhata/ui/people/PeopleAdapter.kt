package com.example.digitalbahikhata.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalbahikhata.data.People
import com.example.digitalbahikhata.databinding.RecyclerViewPeopleBinding

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    var people = mutableListOf<People>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewPeopleBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(val binding: RecyclerViewPeopleBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}