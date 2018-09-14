package com.egecius.shroomzoom

class MainActivityUiMapper {

    fun toResultUi(shroomResult: ShroomResult): ShroomResultUi {
        val textConcatenated = concatenateText(shroomResult)
        return ShroomResultUi(shroomResult.photoBitmap, textConcatenated)
    }

    private fun concatenateText(shroomResult: ShroomResult) : String {
        val stringBuilder = StringBuilder()
        for (item in shroomResult.list) {
            stringBuilder.append(item.shroomName + ": " + item.probability + "\n")
        }

        return stringBuilder.toString()
    }

}