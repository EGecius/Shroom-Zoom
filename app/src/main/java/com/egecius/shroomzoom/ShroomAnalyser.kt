package com.egecius.shroomzoom

import android.graphics.Bitmap
import io.reactivex.Single

open class ShroomAnalyser {

    open fun analyse(photoBitmap: Bitmap): Single<ShroomResult> {
        // TODO: 11/09/2018 implement
        val result = ShroomResult(listOf(
                SingleShroomAnalysis("boletus", 0.90f),
                SingleShroomAnalysis("Chanterelle", 0.02f),
                SingleShroomAnalysis("Boletus badius", 0.70f)
        ), photoBitmap)


        return Single.just(result)
    }
}