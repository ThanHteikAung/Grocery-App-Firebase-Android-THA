package com.tha.grocery.data.modals

import android.graphics.Bitmap
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.network.CloudFireStoreFirebaseApiImpl
import com.tha.grocery.network.FirebaseApi

object GroceryModelImpl : GroceryModel {
    //override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl
    override var mFirebaseApi: FirebaseApi = CloudFireStoreFirebaseApiImpl

    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFailure)
    }

    override fun addGrocery(name: String, description: String, amount: String, image: String) {
        mFirebaseApi.addGrocery(name, description, amount, image)
    }

    override fun deleteGrocery(name: String) {
        mFirebaseApi.deleteGrocery(name)
    }

    override fun uploadImageGrocery(image: Bitmap, grocery: GroceryVO) {
        mFirebaseApi.uploadImageAndEditGrocery(image, grocery)
    }

}