package com.egecius.shroomzoom

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PhotoTakerDelegate(private val activity: AppCompatActivity) {

    fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(takePictureIntent, MainActivity.REQUEST_IMAGE_CAPTURE)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, photoView: ImageView) {

        if (requestCode == MainActivity.REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            if (data != null && data.extras != null) {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                val imageBitmap = data.extras.get("data") as Bitmap

                photoView.setImageBitmap(imageBitmap)
            }
        }
    }
}