package com.egecius.shroomzoom

import android.graphics.Bitmap

data class ShroomResult (
        val list: List<SingleShroomAnalysis>,
        val photoBitmap: Bitmap)

data class SingleShroomAnalysis(val shroomName: String, val probability: Float)