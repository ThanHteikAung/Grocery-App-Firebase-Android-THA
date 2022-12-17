package com.tha.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.mvp.presenters.LoginPresenter
import com.tha.grocery.mvp.views.LoginView
import com.tha.grocery.data.modals.AuthenticationModel
import com.tha.grocery.data.modals.AuthenticationModelImpl
import com.tha.grocery.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    override fun onUiReady(owner: LifecycleOwner) {}

    override fun onTapLogin(email: String, password: String) {
        mAuthenticatioModel.login(email, password, onSuccess = {
            mView.navigateToHomeScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }
}