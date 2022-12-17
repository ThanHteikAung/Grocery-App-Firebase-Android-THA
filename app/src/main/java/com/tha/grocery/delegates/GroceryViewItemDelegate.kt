package com.tha.grocery.delegates

import com.tha.grocery.data.vos.GroceryVO

interface GroceryViewItemDelegate {
    fun onTapDeleteGrocery(name: String)
    fun onTapEditGrocery(name: String, description: String, amount: String)
    fun onTapFileUpload(grocery: GroceryVO)
}