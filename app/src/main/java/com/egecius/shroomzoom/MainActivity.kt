package com.egecius.shroomzoom

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter

    private lateinit var photoView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)
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
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            if (data != null && data.extras != null) {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                val imageBitmap = data.extras.get("data") as Bitmap

                photoView.setImageBitmap(imageBitmap)
            }
        }
    }

    companion object {
         const val REQUEST_IMAGE_CAPTURE = 1
    }

}
