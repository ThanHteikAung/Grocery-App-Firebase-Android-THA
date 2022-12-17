package com.tha.grocery.network

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.tha.grocery.data.vos.GroceryVO
import java.io.ByteArrayOutputStream
import java.util.*

object CloudFireStoreFirebaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()

    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        /*db.collection("groceries")
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
            }*/

        db.collection("groceries")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val groceriesList: MutableList<GroceryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        val grocery = GroceryVO()
                        grocery.name = data?.get("name") as String
                        grocery.description = data["description"] as String
                        grocery.amount = data["amount"] as String
                        grocery.image = data["image"] as String?
                        groceriesList.add(grocery)
                    }
                    onSuccess(groceriesList)
                }

            }
    }

    override fun addGrocery(name: String, description: String, amount: String, image: String) {
        val groceryMap = hashMapOf(
            "name" to name,
            "description" to description,
            "amount" to amount,
            "image" to image
        )
        db.collection("groceries")
            .document(name)
            .set(groceryMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added grocery") }
            .addOnFailureListener { Log.d("Failure", "Failed to add grocery") }
    }

    override fun deleteGrocery(name: String) {
        db.collection("groceries")
            .document(name)
            .delete()
            .addOnSuccessListener { Log.d("Success", "Successfully deleted grocery") }
            .addOnFailureListener { Log.d("Failure", "Failed to delete grocery") }
    }

    override fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO) {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val data = baos.toByteArray()
        val imageRef = storage.reference.child("images/${UUID.randomUUID()}")
        imageRef.putBytes(data)
            .addOnFailureListener { }
            .addOnSuccessListener { }
            .continueWithTask {
                return@continueWithTask imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                val imageUrl = task.result?.toString()
                addGrocery(
                    grocery.name ?: "",
                    grocery.description ?: "",
                    grocery.amount ?: "",
                    imageUrl ?: ""
                )
            }
    }
}