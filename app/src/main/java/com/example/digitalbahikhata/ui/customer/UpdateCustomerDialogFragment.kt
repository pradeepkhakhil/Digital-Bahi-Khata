package com.example.digitalbahikhata.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.digitalbahikhata.R
import com.example.digitalbahikhata.data.Customer
import com.example.digitalbahikhata.databinding.FragmentUpdateCustomerDialogBinding


class UpdateCustomerDialogFragment(private val customer: Customer) : DialogFragment() {

    private var _binding: FragmentUpdateCustomerDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

        _binding = FragmentUpdateCustomerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.editTextFullName.setText(customer.fullName)
        binding.editTextContact.setText(customer.contactNumber)

        binding.buttonUpdate.setOnClickListener {
            val fullName = binding.editTextFullName.text.toString().trim()
            val contactNumber = binding.editTextContact.text.toString().trim()

            if(fullName.isEmpty()) {
                binding.editTextFullName.error = "This field is required"
                return@setOnClickListener
            }

            customer.fullName = fullName
            customer.contactNumber = contactNumber

            customerViewModel.updateCustomer(customer)
            dismiss()
            Toast.makeText(context, "Customer has been updated", Toast.LENGTH_SHORT).show()
        }

    }

}