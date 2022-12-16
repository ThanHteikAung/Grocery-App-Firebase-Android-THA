package com.tha.grocery.network

import com.tha.grocery.data.vos.GroceryVO


interface FirebaseApi {
    fun getGroceries(onSuccess: (groceries: List<GroceryVO>) -> Unit, onFailure: (String) -> Unit)
    fun addGrocery(name: String, description: String, amount: String)
    fun deleteGrocery(name: String)
}