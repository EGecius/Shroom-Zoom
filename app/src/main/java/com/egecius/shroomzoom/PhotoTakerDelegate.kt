package com.egecius.shroomzoom

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class PhotoTakerDelegate(private val activity: AppCompatActivity) {

    private val photosTakenSubject: PublishSubject<Bitmap> = PublishSubject.create()

    fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(takePictureIntent, MainActivity.REQUEST_IMAGE_CAPTURE)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == MainActivity.REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            if (data != null && data.extras != null) {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                val imageBitmap = data.extras.get("data") as Bitmap

                photosTakenSubject.onNext(imageBitmap)
            }
        }
    }

    fun listenToPhotosTaken(): Observable<Bitmap> {
        return photosTakenSubject
    }
}