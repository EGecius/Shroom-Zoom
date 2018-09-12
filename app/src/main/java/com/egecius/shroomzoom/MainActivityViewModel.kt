package com.egecius.shroomzoom

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    lateinit var pictureTakerDelegate: PhotoTakerDelegate
    lateinit var mainActivity: MainActivity

    fun onClickedButtonTakePhoto() {
        dispatchTakePictureIntent()
    }

    private fun dispatchTakePictureIntent() {
        pictureTakerDelegate.dispatchTakePictureIntent()
    }

    fun init(pictureTakerDelegate: PhotoTakerDelegate, mainActivity: MainActivity) {
        this.pictureTakerDelegate = pictureTakerDelegate
        this.mainActivity = mainActivity

        listenToPhotosTaken()
    }

    private fun listenToPhotosTaken() {
        pictureTakerDelegate.listenToPhotosTaken()
                .subscribe { bitmap -> mainActivity.showPhoto(bitmap) }
    }
}