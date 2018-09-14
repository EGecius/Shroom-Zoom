package com.egecius.shroomzoom

class MainActivityUiMapper {

    fun toResultUi(shroomResult: ShroomResult): ShroomResultUi {
        return ShroomResultUi(shroomResult.photoBitmap, concatenateText(shroomResult))
    }

    private fun concatenateText(shroomResult: ShroomResult) : String {
        val stringBuilder = StringBuilder()
        for (item in shroomResult.list) {
            stringBuilder.append(item.shroomName + ": " + item.probability + "\n")
        }

        return stringBuilder.toString()
    }

}