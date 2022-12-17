package com.tha.grocery.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.analytics.PARAMETER_EMAIL
import com.tha.grocery.analytics.SCREEN_LOGIN
import com.tha.grocery.analytics.TAP_LOGIN
import com.tha.grocery.data.modals.AuthenticationModel
import com.tha.grocery.data.modals.AuthenticationModelImpl
import com.tha.grocery.data.modals.GroceryModel
import com.tha.grocery.data.modals.GroceryModelImpl
import com.tha.grocery.mvp.presenters.AbstractBasePresenter
import com.tha.grocery.mvp.presenters.LoginPresenter
import com.tha.grocery.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl
    private val mGroceryModel: GroceryModel = GroceryModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_LOGIN)
        mGroceryModel.setUpRemoteConfigWithDefaultValues()
        mGroceryModel.fetchRemoteConfigs()
    }

    override fun onTapLogin(context: Context, email: String, password: String) {
        sendEventsToFirebaseAnalytics(context, TAP_LOGIN, PARAMETER_EMAIL, email)
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