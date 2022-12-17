package com.tha.grocery.mvp.presenters

import android.content.Context
import com.tha.grocery.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, email: String, password: String, userName: String)
}