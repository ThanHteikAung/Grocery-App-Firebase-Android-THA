package com.tha.grocery.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tha.grocery.data.vos.GroceryVO

object CloudFireStoreFirebaseApiImpl : FirebaseApi {

    val db = Firebase.firestore

    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("groceries")
            .get()
            .addOnSuccessListener { result ->
                val groceriesList: MutableList<GroceryVO> = arrayListOf()
                for(document in result){
                    val data = document.data
                    var grocery = GroceryVO()
                    grocery.name = data["name"] as String
                    grocery.description = data["description"] as String
                    grocery.amount = data["amount"] as String
                    groceriesList.add(grocery)
                }
                onSuccess(groceriesList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Please check connection")
            }
    }

    override fun addGrocery(name: String, description: String, amount: String) {

    }

    override fun deleteGrocery(name: String) {

    }
}