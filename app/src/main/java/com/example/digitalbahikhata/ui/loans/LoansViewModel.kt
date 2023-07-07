package com.example.digitalbahikhata.ui.loans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoansViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Your Loans will show here"
    }
    val text: LiveData<String> = _text

}