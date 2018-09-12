package com.egecius.shroomzoom

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    lateinit var pictureTakerDelegate: PhotoTakerDelegate


    fun onClickedButtonTakePhoto() {
        // TODO: 11/09/2018  
    }

    fun setPictureTaker(pictureTakerDelegate: PhotoTakerDelegate) {
        this.pictureTakerDelegate = pictureTakerDelegate
    }
}