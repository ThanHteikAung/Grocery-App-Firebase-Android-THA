package com.tha.grocery.mvp.presenters

import com.tha.grocery.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView>{
    fun onTapLogin(email: String, password: String)
    fun onTapRegister()
}