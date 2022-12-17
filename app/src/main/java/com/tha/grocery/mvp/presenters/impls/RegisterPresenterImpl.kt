package com.tha.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.mvp.presenters.RegisterPresenter
import com.tha.grocery.mvp.views.RegisterView
import com.tha.grocery.data.modals.AuthenticationModel
import com.tha.grocery.data.modals.AuthenticationModelImpl
import com.tha.grocery.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(email: String, password: String, userName: String) {
        mAuthenticationModel.register(email, password, userName, onSuccess = {
            mView.navigateToToLoginScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}