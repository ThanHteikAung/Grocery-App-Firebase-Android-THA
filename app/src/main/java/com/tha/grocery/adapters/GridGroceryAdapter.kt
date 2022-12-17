package com.tha.grocery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tha.grocery.view.viewholders.GroceryViewHolder
import com.tha.grocery.R
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.delegates.GroceryViewItemDelegate
import com.tha.grocery.view.viewholders.GridGroceryViewHolder

class GridGroceryAdapter(private val mDelegate: GroceryViewItemDelegate) : BaseRecyclerAdapter<GridGroceryViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridGroceryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_grocery_grid_item, parent, false)
        return GridGroceryViewHolder(view,mDelegate)
    }
}