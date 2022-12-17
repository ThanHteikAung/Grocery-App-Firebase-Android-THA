package com.tha.grocery.data.modals

import android.graphics.Bitmap
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.network.CloudFireStoreFirebaseApiImpl
import com.tha.grocery.network.FirebaseApi
import com.tha.grocery.network.remoteconfig.FirebaseRemoteConfigManager

object GroceryModelImpl : GroceryModel {
    //override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl
    override var mFirebaseApi: FirebaseApi = CloudFireStoreFirebaseApiImpl

    override var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager = FirebaseRemoteConfigManager

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

    override fun setUpRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfigManager.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfigs() {
        mFirebaseRemoteConfigManager.fetchRemoteConfigs()
    }

    override fun getAppNameFromRemoteConfig(): String {
        return mFirebaseRemoteConfigManager.getToolbarName()
    }

    override fun getGridViewFromRemoteConfig(): Int {
        return mFirebaseRemoteConfigManager.getGridViewNumber()
    }

}