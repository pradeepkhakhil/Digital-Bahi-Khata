package com.example.digitalbahikhata.data

import com.google.firebase.database.Exclude

data class Customer(
    @get:Exclude
    var id: String? = null,
    var fullName: String? = null,
    var contactNumber: String? = null
) {
}