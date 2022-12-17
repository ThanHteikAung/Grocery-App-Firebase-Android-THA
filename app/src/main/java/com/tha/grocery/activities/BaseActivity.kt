package com.tha.grocery.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.tha.grocery.mvp.presenters.AbstractBasePresenter
import com.tha.grocery.mvp.views.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    inline fun <reified T : AbstractBasePresenter<W>, reified W : BaseView> getPresenter(): T {
        val presenter = ViewModelProvider(this)[T::class.java]
        if (this is W) presenter.initPresenter(this)
        return presenter
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }
}