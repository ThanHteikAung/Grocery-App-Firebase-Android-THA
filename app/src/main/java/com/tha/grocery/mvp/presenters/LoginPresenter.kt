package com.tha.grocery.mvp.presenters

import android.content.Context
import com.tha.grocery.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(context: Context, email: String, password: String)
    fun onTapRegister()
}