package com.egecius.shroomzoom

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import io.reactivex.Observable

class MainActivityPresenter : ViewModel() {

    private lateinit var mainView: MainView
    private lateinit var photosObservable: Observable<Bitmap>
    private lateinit var shroomAnalyser: ShroomAnalyser

    fun init(mainView: MainView, photosObservable: Observable<Bitmap>, shroomAnalyser: ShroomAnalyser) {
        this.mainView = mainView
        this.photosObservable = photosObservable
        this.shroomAnalyser = shroomAnalyser

        listenToPhotosTaken()
    }

    private fun listenToPhotosTaken() {
        photosObservable
                .subscribe { bitmap ->
                    shroomAnalyser.analyse(bitmap)
                    mainView.showPhoto(bitmap) }
    }

    fun onClickedButtonTakePhoto() {
        mainView.takePhoto()
    }

}

interface MainView {
    fun showPhoto(bitmap: Bitmap)
    fun takePhoto()
}