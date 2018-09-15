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

@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    private lateinit var sut: MainActivityPresenter

    private val mShroomResult = ShroomResultBuilder.aShroomResult().build()
    private var photoTakenBitmap = mShroomResult.photoBitmap
    private var publishForPhotosTaken = PublishSubject.create<Bitmap>()

    @Mock
    lateinit var mainView: MainView
    @Mock
    lateinit var shroomAnalyser: ShroomAnalyser

    @Before
    fun setUp() {
        sut = MainActivityPresenter()
        sut.init(mainView, publishForPhotosTaken, shroomAnalyser)

        given(shroomAnalyser.analyse(photoTakenBitmap)).willReturn(
                Single.just(mShroomResult))
    }

    @Test
    fun `shows photo when view taken`() {
        whenPhotoTaken()

        verify<MainView>(mainView).showResults(mShroomResult)
    }

    @Test
    fun `shroom analyser is called when photo taken`() {
        whenPhotoTaken()

        verify<ShroomAnalyser>(shroomAnalyser).analyse(photoTakenBitmap)
    }

    @Test
    fun `takes photo when requested`() {
        sut.onClickedButtonTakePhoto()

        verify<MainView>(mainView).takePhoto()
    }

    private fun whenPhotoTaken() {
        publishForPhotosTaken.onNext(photoTakenBitmap)
    }

}