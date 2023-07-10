package com.example.digitalbahikhata.ui.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalbahikhata.data.Customer
import com.example.digitalbahikhata.databinding.RecyclerViewCustomerBinding

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.ViewHolder>(), Filterable {

    var customers = mutableListOf<Customer>()
    var customersBkp = mutableListOf<Customer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewCustomerBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewName.text = customers[position].fullName
        holder.binding.textViewContact.text = customers[position].contactNumber
    }

    fun addCustomer(customer: Customer) {
        if(!customers.contains(customer)) {
            customers.add(customer)
            customersBkp.add(customer)
        } else {
            val index = customers.indexOf(customer)
            customers[index] = customer
        }
        notifyDataSetChanged()
    }

    fun setFilterCustomerList(customerList: MutableList<Customer>) {
        this.customers = customerList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString().orEmpty().lowercase()
                return Filter.FilterResults().apply {
                    values = when {
                        queryString.isEmpty() -> customersBkp
                        else -> customersBkp.filter {
                            it.fullName?.lowercase()?.contains(queryString) == true ||
                                    it.contactNumber?.lowercase()?.contains(queryString) == true
                        }
                    }
                }
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                customers = (filterResults.values as List<Customer>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(val binding: RecyclerViewCustomerBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}