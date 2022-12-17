package com.tha.grocery.data.modals

import com.tha.grocery.network.auth.AuthManager

interface AuthenticationModel {
    var mAuthManager: AuthManager

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
        email: String,
        password: String,
        userName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserName(): String
}