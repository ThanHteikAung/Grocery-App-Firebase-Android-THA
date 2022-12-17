package com.tha.grocery.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.marginTop
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tha.grocery.R
import com.tha.grocery.adapters.GridGroceryAdapter
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
import java.io.IOException

class MainActivity : BaseActivity(), MainView {

    private lateinit var mAdapter: GroceryAdapter
    private lateinit var mGridGroceryAdapter: GridGroceryAdapter
    private lateinit var mPresenter: MainPresenter

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setUpPresenter()
        setUpRecyclerView()
        setUpGridRecyclerView()

        setUpActionListeners()

        mPresenter.onUiReady(this, this)

        addCrashButton()

    }

    private fun addCrashButton(){
        val crashButton = Button(this)
        crashButton.text = "Crash!"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
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

    private fun setUpGridRecyclerView() {
        mGridGroceryAdapter = GridGroceryAdapter(mPresenter)
        rvGridGroceries.adapter = mGridGroceryAdapter
        rvGridGroceries.layoutManager =
            GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
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
        mGridGroceryAdapter.setNewData(groceryList)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showGroceryDialog(name: String, description: String, amount: String) {
        val groceryDialog = GroceryDialogFragment.newFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_NAME, name)
        bundle.putString(BUNDLE_DESCRIPTION, description)
        bundle.putString(BUNDLE_AMOUNT, amount)
        groceryDialog.arguments = bundle
        groceryDialog.show(supportFragmentManager, GroceryDialogFragment.TAG_ADD_GROCERY_DIALOG)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null || data?.data != null) {
                    val filePath = data.data
                    try {
                        filePath?.let {
                            if (Build.VERSION.SDK_INT >= 29) {
                                val source: ImageDecoder.Source =
                                    ImageDecoder.createSource(this.contentResolver, it)

                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mPresenter.onPhotoTaken(bitmap)
                            } else {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    this.contentResolver,
                                    filePath
                                )
                                mPresenter.onPhotoTaken(bitmap)
                            }
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    override fun displayToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun displayGirdView(number: Int) {
        if (number == 0) {
            rvGroceries.visibility = View.VISIBLE
            rvGridGroceries.visibility = View.GONE
        } else {
            rvGroceries.visibility = View.GONE
            rvGridGroceries.visibility = View.VISIBLE
        }
    }
}