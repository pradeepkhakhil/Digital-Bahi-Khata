package com.example.digitalbahikhata.data

import com.google.firebase.database.Exclude

data class Customer(
    @get:Exclude
    var id: String? = null,
    var fullName: String? = null,
    var contactNumber: String? = null,
    @get:Exclude
    var isDeleted: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Customer) {
            other.id == id
        }else false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (contactNumber?.hashCode() ?: 0)
        result = 31 * result + isDeleted.hashCode()
        return result
    }


}