package com.tha.grocery.data.modals

import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.network.FirebaseApi

interface GroceryModel {

    var mFirebaseApi: FirebaseApi

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFailure: (String) -> Unit)
}