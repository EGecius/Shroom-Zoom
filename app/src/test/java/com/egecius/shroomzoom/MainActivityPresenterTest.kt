package com.egecius.shroomzoom

import android.graphics.Bitmap
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    lateinit var sut: MainActivityPresenter

    @Mock
    lateinit var mainView: MainView
    @Mock
    lateinit var photoTakenBitmap: Bitmap
    @Mock
    lateinit var shroomAnalyser: ShroomAnalyser

    var analysisList: List<SingleShroomAnalysis> = ArrayList()
    private lateinit var mShroomResult: ShroomResult
    private var publishForPhotosTaken = PublishSubject.create<Bitmap>()

    @Before
    fun setUp() {
        sut = MainActivityPresenter()
        sut.init(mainView, publishForPhotosTaken, shroomAnalyser)

        mShroomResult = ShroomResult(analysisList, photoTakenBitmap)

        given(shroomAnalyser.analyse(photoTakenBitmap)).willReturn(
                Single.just(mShroomResult))
    }

    @Test
    fun showsPhotoWhenViewTaken() {
        whenPhotoTaken()

        verify<MainView>(mainView).showResults(mShroomResult)
    }


    @Test
    fun shroomAnalyserIsCalledWhenPhotoTaken() {
        whenPhotoTaken()

        verify<ShroomAnalyser>(shroomAnalyser).analyse(photoTakenBitmap)
    }

    @Test
    fun takesPhotoWhenRequested() {
        sut.onClickedButtonTakePhoto()

        verify<MainView>(mainView).takePhoto()
    }

    private fun whenPhotoTaken() {
        publishForPhotosTaken.onNext(photoTakenBitmap)
    }

}