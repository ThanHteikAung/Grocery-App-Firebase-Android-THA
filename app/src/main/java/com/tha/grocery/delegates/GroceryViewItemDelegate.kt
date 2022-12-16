package com.tha.grocery.delegates

interface GroceryViewItemDelegate {
    fun onTapDeleteGrocery(name: String)
    fun onTapEditGrocery(name: String, description: String, amount: String)
}