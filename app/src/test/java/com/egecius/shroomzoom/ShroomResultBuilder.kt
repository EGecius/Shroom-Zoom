package com.egecius.shroomzoom

import android.graphics.Bitmap
import org.mockito.Mockito
import java.util.*

class ShroomResultBuilder {

    internal fun build(): ShroomResult {
        val mockedBitmap = Mockito.mock(Bitmap::class.java)
        return ShroomResult(ArrayList(), mockedBitmap)
    }

    companion object {
        fun aShroomResult(): ShroomResultBuilder {
            return ShroomResultBuilder()
        }
    }

}
