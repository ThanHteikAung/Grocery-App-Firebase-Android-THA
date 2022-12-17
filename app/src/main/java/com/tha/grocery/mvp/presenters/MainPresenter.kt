package com.tha.grocery.mvp.presenters

import android.graphics.Bitmap
import com.tha.grocery.delegates.GroceryViewItemDelegate
import com.tha.grocery.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, GroceryViewItemDelegate {

    fun onTapAddGrocery(name: String, description: String, amount: String)
    fun onPhotoTaken(bitmap: Bitmap)

}
