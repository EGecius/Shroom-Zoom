package com.egecius.shroomzoom

data class ShroomAnalysisResult (val list: List<SingleShroomAnalysis>)

data class SingleShroomAnalysis(val shroomName: String, val probability: Float)