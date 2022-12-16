package com.tha.grocery.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tha.grocery.R
import com.tha.grocery.adapters.GroceryAdapter
import com.tha.grocery.data.vos.GroceryVO
import com.tha.grocery.dialogs.GroceryDialogFragment
import com.tha.grocery.dialogs.GroceryDialogFragment.Companion.BUNDLE_AMOUNT
import com.tha.grocery.dialogs.GroceryDialogFragment.Companion.BUNDLE_DESCRIPTION
import com.tha.grocery.dialogs.GroceryDialogFragment.Companion.BUNDLE_NAME
import com.tha.grocery.mvp.presenters.MainPresenter
import com.tha.grocery.mvp.presenters.impls.MainPresenterImpl
import com.tha.grocery.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    private lateinit var mAdapter: GroceryAdapter
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setUpPresenter()
        setUpRecyclerView()

        setUpActionListeners()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpActionListeners() {
        fab.setOnClickListener {
            GroceryDialogFragment.newFragment()
                .show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = GroceryAdapter(mPresenter)
        rvGroceries.adapter = mAdapter
        rvGroceries.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showGroceryData(groceryList: List<GroceryVO>) {
        mAdapter.setNewData(groceryList)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showGroceryDialog(name: String, description: String, amount: String) {
        val groceryDialog = GroceryDialogFragment.newFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_NAME, name)
        bundle.putString(BUNDLE_DESCRIPTION,description)
        bundle.putString(BUNDLE_AMOUNT, amount)
        groceryDialog.arguments = bundle
        groceryDialog.show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
    }
}