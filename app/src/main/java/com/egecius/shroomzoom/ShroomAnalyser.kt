package com.egecius.shroomzoom

import android.graphics.Bitmap
import io.reactivex.Single

class ShroomAnalyser {

    fun analyse(photo: Bitmap): Single<ShroomAnalysisResult> {
        // TODO: 11/09/2018 implement
        val result = ShroomAnalysisResult(listOf(
                SingleShroomAnalysis("boletus", 0.90f),
                SingleShroomAnalysis("Chanterelle", 0.02f),
                SingleShroomAnalysis("Boletus badius", 0.70f)
        ))

        return Single.just(result)
    }
}