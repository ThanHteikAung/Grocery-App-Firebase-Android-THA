package com.tha.grocery.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tha.grocery.R
import com.tha.grocery.mvp.presenters.LoginPresenter
import com.tha.grocery.mvp.presenters.impls.LoginPresenterImpl
import com.tha.grocery.mvp.views.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEmail.text.toString(), etPassword.text.toString())
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}