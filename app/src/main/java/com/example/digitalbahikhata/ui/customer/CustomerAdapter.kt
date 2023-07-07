package com.example.digitalbahikhata.ui.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalbahikhata.data.Customer
import com.example.digitalbahikhata.databinding.RecyclerViewCustomerBinding

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    var people = mutableListOf<Customer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewCustomerBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(val binding: RecyclerViewCustomerBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}