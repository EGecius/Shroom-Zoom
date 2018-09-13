package com.egecius.shroomzoom

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import io.reactivex.Observable

class MainActivityPresenter : ViewModel() {

    lateinit var mainActivity: MainView
    lateinit var photosObservable: Observable<Bitmap>

    fun init(mainActivity: MainView, photosObservable: Observable<Bitmap>) {
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

interface MainView {
    fun showPhoto(bitmap: Bitmap)
    fun takePhoto()
}