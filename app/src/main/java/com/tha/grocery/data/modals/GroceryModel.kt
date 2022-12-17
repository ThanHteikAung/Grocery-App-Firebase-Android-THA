package com.tha.grocery.data.modals

import android.graphics.Bitmap
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.network.FirebaseApi
import com.tha.grocery.network.remoteconfig.FirebaseRemoteConfigManager

interface GroceryModel {

    var mFirebaseApi: FirebaseApi
    var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFailure: (String) -> Unit)
    fun addGrocery(name: String, description: String, amount: String, image: String)
    fun deleteGrocery(name: String)
    fun uploadImageGrocery(image: Bitmap, grocery: GroceryVO)
    fun setUpRemoteConfigWithDefaultValues()
    fun fetchRemoteConfigs()
    fun getAppNameFromRemoteConfig(): String
    fun getGridViewFromRemoteConfig(): Int
}