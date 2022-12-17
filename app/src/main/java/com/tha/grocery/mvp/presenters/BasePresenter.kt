package com.tha.grocery.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.tha.grocery.mvp.views.BaseView

interface BasePresenter<V : BaseView> {
    fun onUiReady(context: Context, owner: LifecycleOwner)
    fun initPresenter(view: V)
}