package com.egecius.shroomzoom

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mPresenter: MainActivityPresenter
    private lateinit var pictureTakerDelegate: PhotoTakerDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenterAndDelegate()
        setupButton()
    }

    private fun initPresenterAndDelegate() {
        pictureTakerDelegate = PhotoTakerDelegate(this)
        mPresenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)
        mPresenter.init(this, pictureTakerDelegate.listenToPhotosTaken())
    }

    private fun setupButton() {
        takePhotoButton.setOnClickListener {
            mPresenter.onClickedButtonTakePhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pictureTakerDelegate.onActivityResult(requestCode, resultCode, data)
    }

    fun showPhoto(bitmap: Bitmap) {
        photoView.setImageBitmap(bitmap)
    }

    fun takePhoto() {
        pictureTakerDelegate.requestTakingPicture()
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

}
