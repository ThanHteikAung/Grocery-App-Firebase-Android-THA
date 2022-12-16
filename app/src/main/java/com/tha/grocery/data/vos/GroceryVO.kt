package com.tha.grocery.data.vos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class GroceryVO (
    var name: String? = "",
    var description: String? = "",
    var amount: String? = ""
)