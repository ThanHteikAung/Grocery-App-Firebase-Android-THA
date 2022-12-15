package com.tha.grocery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tha.grocery.view.viewholders.GroceryViewHolder
import com.tha.grocery.R
import com.tha.grocery.data.vos.GroceryVO

class GroceryAdapter() : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_grocery_item, parent, false)
        return GroceryViewHolder(view)
    }
}