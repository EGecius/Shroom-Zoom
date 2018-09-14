package com.egecius.shroomzoom

import android.graphics.Bitmap
import io.reactivex.Single

@AllOpen
class ShroomAnalyser {

    fun analyse(photoBitmap: Bitmap): Single<ShroomResult> {
        // TODO: 11/09/2018 implement properly
        val result = createMockResult(photoBitmap)
        return Single.just(result)
    }

    fun createMockResult(photoBitmap: Bitmap): ShroomResult {
        return ShroomResult(listOf(
                SingleShroomAnalysis("Boletus", 0.90f),
                SingleShroomAnalysis("Chanterelle", 0.02f),
                SingleShroomAnalysis("Boletus badius", 0.70f)
        ), photoBitmap)
    }
}