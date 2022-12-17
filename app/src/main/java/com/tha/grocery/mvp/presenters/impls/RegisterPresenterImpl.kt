package com.tha.grocery.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.analytics.PARAMETER_EMAIL
import com.tha.grocery.analytics.SCREEN_REGISTER
import com.tha.grocery.analytics.TAP_REGISTER
import com.tha.grocery.data.modals.AuthenticationModel
import com.tha.grocery.data.modals.AuthenticationModelImpl
import com.tha.grocery.mvp.presenters.AbstractBasePresenter
import com.tha.grocery.mvp.presenters.RegisterPresenter
import com.tha.grocery.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(
        context: Context,
        email: String,
        password: String,
        userName: String
    ) {
        sendEventsToFirebaseAnalytics(context, TAP_REGISTER, PARAMETER_EMAIL, email)
        mAuthenticationModel.register(email, password, userName, onSuccess = {
            mView.navigateToToLoginScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        sendEventsToFirebaseAnalytics(context, SCREEN_REGISTER)
    }
}