package com.tha.grocery.mvp.views

import com.tha.grocery.data.vos.GroceryVO

interface MainView : BaseView {
    fun showGroceryData(groceryList: List<GroceryVO>)
    fun showErrorMessage(message: String)
}