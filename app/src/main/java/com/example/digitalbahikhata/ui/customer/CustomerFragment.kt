package com.example.digitalbahikhata.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.digitalbahikhata.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {

    private var _binding: FragmentCustomerBinding? = null
    private val binding get() = _binding!!
    private val adapter = CustomerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val customerViewModel =
            ViewModelProvider(this).get(CustomerViewModel::class.java)

        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewPeople.adapter = adapter

        binding.addButton.setOnClickListener {
            AddCustomerDialogFragment().show(childFragmentManager, "")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}