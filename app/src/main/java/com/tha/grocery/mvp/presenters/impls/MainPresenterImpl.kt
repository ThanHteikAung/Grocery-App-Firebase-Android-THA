package com.tha.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.mvp.presenters.AbstractBasePresenter
import com.tha.grocery.mvp.presenters.MainPresenter
import com.tha.grocery.data.modals.GroceryModelImpl
import com.tha.grocery.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mGroceryModel = GroceryModelImpl
    override fun onTapAddGrocery(name: String, description: String, amount: String) {
        mGroceryModel.addGrocery(name,description,amount)
    }

    override fun onTapDeleteGrocery(name: String) {
        mGroceryModel.deleteGrocery(name)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFailure = {
                mView.showErrorMessage(it)
            }
        )
    }
}