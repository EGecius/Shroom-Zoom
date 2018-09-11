package com.egecius.shroomzoom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var pictureTakerDelegate: PhotoTakerDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)
        pictureTakerDelegate = PhotoTakerDelegate(this)
        setupButton()
    }

    private fun setupButton() {
        takePhotoButton.setOnClickListener {
            presenter.onClickedButtonTakePhoto()
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        pictureTakerDelegate.dispatchTakePictureIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pictureTakerDelegate.onActivityResult(requestCode, resultCode, data, photoView)
    }

    companion object {
         const val REQUEST_IMAGE_CAPTURE = 1
    }

}
