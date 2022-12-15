package com.tha.grocery.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.mvp.views.BaseView

interface BasePresenter<V: BaseView> {
    fun onUiReady(owner: LifecycleOwner)
    fun initPresenter(view: V)
}