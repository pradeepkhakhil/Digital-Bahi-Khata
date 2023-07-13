package com.example.digitalbahikhata.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digitalbahikhata.data.Customer
import com.example.digitalbahikhata.data.NODE_CUSTOMERS
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class CustomerViewModel : ViewModel() {

    private val dbcustomers = FirebaseDatabase.getInstance().getReference(NODE_CUSTOMERS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _customer = MutableLiveData<Customer>()
    val customer: LiveData<Customer> get() = _customer

    fun addCustomer(customer: Customer) {
        customer.id = dbcustomers.push().key
        dbcustomers.child(customer.id!!).setValue(customer).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
    }

    private val childEventListener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val customer = snapshot.getValue(Customer::class.java)
            customer?.id = snapshot.key
            _customer.value = customer!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            val customer = snapshot.getValue(Customer::class.java)
            customer?.id = snapshot.key
            _customer.value = customer!!
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val customer = snapshot.getValue(Customer::class.java)
            customer?.id = snapshot.key
            customer?.isDeleted = true
            _customer.value = customer!!
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }

    fun getRealtimeUpdate() {
        dbcustomers.addChildEventListener(childEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        dbcustomers.removeEventListener(childEventListener)
    }

    fun updateCustomer(customer: Customer) {
        dbcustomers.child(customer.id!!).setValue(customer).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
    }

    fun deleteCustomer(customer: Customer) {
        dbcustomers.child(customer.id!!).setValue(null).addOnCompleteListener {
            if (it.isSuccessful) {
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
    }

}