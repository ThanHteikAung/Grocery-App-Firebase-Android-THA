package com.tha.grocery.mvp.presenters

import com.tha.grocery.delegates.GroceryViewItemDelegate
import com.tha.grocery.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, GroceryViewItemDelegate {

    fun onTapAddGrocery(name: String, description: String, amount: String)

}
