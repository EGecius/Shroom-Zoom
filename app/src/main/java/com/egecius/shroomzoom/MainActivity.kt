package com.egecius.shroomzoom

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var pictureTakerDelegate: PhotoTakerDelegate

    private lateinit var photoView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)
        pictureTakerDelegate = PhotoTakerDelegate(this)
        setupButton()
        photoView = findViewById(R.id.photo)
    }

    private fun setupButton() {
        findViewById<View>(R.id.take_photo).setOnClickListener {
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
