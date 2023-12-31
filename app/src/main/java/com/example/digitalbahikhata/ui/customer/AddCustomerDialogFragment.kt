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
import com.example.digitalbahikhata.databinding.FragmentAddCustomerDialogBinding


class AddCustomerDialogFragment : DialogFragment() {

    private var _binding: FragmentAddCustomerDialogBinding? = null
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

        _binding = FragmentAddCustomerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        customerViewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.customer_added)
            } else {
                getString(R.string.error, it.message)
            }

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        binding.buttonAdd.setOnClickListener {
            val fullName = binding.editTextFullName.text.toString().trim()
            val contactNumber = binding.editTextContact.text.toString().trim()

            if(fullName.isEmpty()) {
                binding.editTextFullName.error = "This field is required"
                return@setOnClickListener
            }

            val customer = Customer()
            customer.fullName = fullName
            customer.contactNumber = contactNumber

            customerViewModel.addCustomer(customer)
        }

    }

}