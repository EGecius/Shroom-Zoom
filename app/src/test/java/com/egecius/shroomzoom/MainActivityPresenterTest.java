package com.egecius.shroomzoom;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    MainActivityPresenter sut;

    @Mock
    MainView mainView;
    @Mock
    Bitmap photoTakenBitmap;
    @Mock
    ShroomAnalyser shroomAnalyser;

    List<SingleShroomAnalysis> analysisList = new ArrayList<>();
    ShroomResult mShroomResult;
    PublishSubject<Bitmap> publishForPhotosTaken = PublishSubject.create();

    @Before
    public void setUp() {
        sut = new MainActivityPresenter();
        sut.init(mainView, publishForPhotosTaken, shroomAnalyser);

        mShroomResult = new ShroomResult(analysisList,
                photoTakenBitmap);

        given(shroomAnalyser.analyse(photoTakenBitmap)).willReturn(
                Single.just(mShroomResult));
    }

    @Test
    public void showsPhotoWhenViewTaken() {
        whenPhotoTaken();

        verify(mainView).showPhoto(mShroomResult);
    }


    @Test
    public void shroomAnalyserIsCalledWhenPhotoTaken() {
        whenPhotoTaken();

        verify(shroomAnalyser).analyse(photoTakenBitmap);
    }

    @Test
    public void takesPhotoWhenRequested() {
        sut.onClickedButtonTakePhoto();

        verify(mainView).takePhoto();
    }

    private void whenPhotoTaken() {
        publishForPhotosTaken.onNext(photoTakenBitmap);
    }

}