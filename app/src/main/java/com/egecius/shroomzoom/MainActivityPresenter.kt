package com.egecius.shroomzoom

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import io.reactivex.Observable

class MainActivityPresenter : ViewModel() {

    lateinit var mainView: MainView
    lateinit var photosObservable: Observable<Bitmap>

    fun init(mainView: MainView, photosObservable: Observable<Bitmap>) {
        this.mainView = mainView
        this.photosObservable = photosObservable

        listenToPhotosTaken()
    }

    private fun listenToPhotosTaken() {
        photosObservable
                .subscribe { bitmap -> mainView.showPhoto(bitmap) }
    }

    fun onClickedButtonTakePhoto() {
        mainView.takePhoto()
    }

}

interface MainView {
    fun showPhoto(bitmap: Bitmap)
    fun takePhoto()
}