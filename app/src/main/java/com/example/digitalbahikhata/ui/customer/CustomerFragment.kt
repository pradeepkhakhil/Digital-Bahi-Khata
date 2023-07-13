package com.example.digitalbahikhata.ui.customer

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalbahikhata.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {

    private var _binding: FragmentCustomerBinding? = null
    private val binding get() = _binding!!
    private val adapter = CustomerAdapter()

    private lateinit var viewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText!!)
                return false
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewCustomer.adapter = adapter

        binding.addButton.setOnClickListener {
            AddCustomerDialogFragment().show(childFragmentManager, "")
        }

        viewModel.customer.observe(viewLifecycleOwner, Observer {
            adapter.addCustomer(it)
        })
        viewModel.getRealtimeUpdate()

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewCustomer)
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val currentCustomer = adapter.customers[position]

            when(direction) {
                ItemTouchHelper.RIGHT -> {
                    UpdateCustomerDialogFragment(currentCustomer).show(childFragmentManager, "")
                }

                ItemTouchHelper.LEFT -> {
                    AlertDialog.Builder(requireContext()).also {
                        it.setTitle("Are you sure you want to delete this Customer?")
                        it.setPositiveButton("Yes") { dialog, which ->
                            viewModel.deleteCustomer(currentCustomer)
                            binding.recyclerViewCustomer.adapter?.notifyItemRemoved(position)
                            Toast.makeText(context, "Customer has been deleted", Toast.LENGTH_SHORT).show()
                        }
                    }.create().show()
                }

            }
            binding.recyclerViewCustomer.adapter?.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}