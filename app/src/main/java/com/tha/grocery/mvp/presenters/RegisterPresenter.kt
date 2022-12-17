package com.tha.grocery.mvp.presenters

import com.tha.grocery.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(email: String, password: String, userName: String)
}