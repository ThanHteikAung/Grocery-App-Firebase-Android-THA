package com.tha.grocery.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.tha.grocery.R
import kotlinx.android.synthetic.main.activity_mlkit.*

class MLKitActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlkit)
        setUpListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == INTENT_REQUEST_CODE_SELECT_IMAGE_FROM_GALLERY) {
            val imageUri = data?.data
            imageUri?.let { image -> ivImage.setImageURI(image) }
        }
    }

    private fun setUpListeners() {
        btnTakePhoto.setOnClickListener {
            selectImageFromGallery()
        }

        btnFindFace.setOnClickListener {

        }

        btnFindText.setOnClickListener {

        }
    }

}