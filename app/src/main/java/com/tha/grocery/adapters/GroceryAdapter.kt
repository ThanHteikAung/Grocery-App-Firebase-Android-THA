package com.tha.grocery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tha.grocery.view.viewholders.GroceryViewHolder
import com.tha.grocery.R
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.delegates.GroceryViewItemDelegate

class GroceryAdapter(private val mDelegate: GroceryViewItemDelegate) : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_grocery_item, parent, false)
        return GroceryViewHolder(view,mDelegate)
    }
}