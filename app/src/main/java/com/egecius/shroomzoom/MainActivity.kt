package com.egecius.shroomzoom

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var pictureTakerDelegate: PhotoTakerDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pictureTakerDelegate = PhotoTakerDelegate(this)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.init(pictureTakerDelegate, this)
        setupButton()
    }

    private fun setupButton() {
        takePhotoButton.setOnClickListener {
            viewModel.onClickedButtonTakePhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pictureTakerDelegate.onActivityResult(requestCode, resultCode, data)
    }

    fun showPhoto(bitmap: Bitmap) {
        photoView.setImageBitmap(bitmap)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

}
