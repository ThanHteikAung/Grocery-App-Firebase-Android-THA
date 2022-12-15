package com.tha.grocery.data.modals

import com.tha.grocery.network.RealtimeDatabaseFirebaseApiImpl
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.network.FirebaseApi

object GroceryModelImpl : GroceryModel {
    override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl

    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFailure)
    }

    override fun addGrocery(name: String, description: String, amount: String) {
        mFirebaseApi.addGrocery(name,description,amount)
    }
}