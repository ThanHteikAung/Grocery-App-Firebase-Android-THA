package com.tha.grocery.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.analytics.SCREEN_HOME
import com.tha.grocery.data.modals.GroceryModelImpl
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.mvp.presenters.AbstractBasePresenter
import com.tha.grocery.mvp.presenters.MainPresenter
import com.tha.grocery.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mGroceryModel = GroceryModelImpl
    private var mChosenGroceryForFileUpload: GroceryVO? = null

    override fun onTapAddGrocery(name: String, description: String, amount: String) {
        mGroceryModel.addGrocery(name, description, amount, "")
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mChosenGroceryForFileUpload?.let {
            mGroceryModel.uploadImageGrocery(bitmap, it)
        }
    }

    override fun onTapFileUpload(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
        mView.openGallery()
    }

    override fun onTapDeleteGrocery(name: String) {
        mGroceryModel.deleteGrocery(name)
    }

    override fun onTapEditGrocery(name: String, description: String, amount: String) {
        mView.showGroceryDialog(name, description, amount)
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_HOME)
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFailure = {
                mView.showErrorMessage(it)
            }
        )
        mView.displayToolbarTitle(mGroceryModel.getAppNameFromRemoteConfig())
        mView.displayGirdView(mGroceryModel.getGridViewFromRemoteConfig())
    }
}