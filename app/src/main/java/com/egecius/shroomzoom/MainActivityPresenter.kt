package com.egecius.shroomzoom

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import io.reactivex.Observable

class MainActivityPresenter : ViewModel() {

    lateinit var mainActivity: MainActivity
    lateinit var photosObservable: Observable<Bitmap>

    fun init(mainActivity: MainActivity, photosObservable: Observable<Bitmap>) {
        this.mainActivity = mainActivity
        this.photosObservable = photosObservable

        listenToPhotosTaken()
    }

    private fun listenToPhotosTaken() {
        photosObservable
                .subscribe { bitmap -> mainActivity.showPhoto(bitmap) }
    }

    fun onClickedButtonTakePhoto() {
        mainActivity.takePhoto()
    }

}