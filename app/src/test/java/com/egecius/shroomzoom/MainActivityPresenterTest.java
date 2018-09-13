package com.egecius.shroomzoom;

import static org.mockito.Mockito.verify;

import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

    PublishSubject<Bitmap> publishForPhotosTaken = PublishSubject.create();

    @Before
    public void setUp() {
        sut = new MainActivityPresenter();
        sut.init(mainView, publishForPhotosTaken, shroomAnalyser);
    }

    @Test
    public void showsPhotoWhenViewTaken() {
        whenPhotoTaken();

        verify(mainView).showPhoto(photoTakenBitmap);
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