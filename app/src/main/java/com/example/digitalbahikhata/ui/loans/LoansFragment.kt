package com.example.digitalbahikhata.ui.loans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.digitalbahikhata.databinding.FragmentDashboardBinding
import com.example.digitalbahikhata.databinding.FragmentLoansBinding
import com.example.digitalbahikhata.ui.dashboard.DashboardViewModel

class LoansFragment : Fragment() {

    private var _binding: FragmentLoansBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loansViewModel =
            ViewModelProvider(this).get(LoansViewModel::class.java)

        _binding = FragmentLoansBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLoans
        loansViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}