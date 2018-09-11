package com.egecius.shroomzoom

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)
        setupButton()
    }

    private fun setupButton() {
        findViewById<Button>(R.id.take_photo).setOnClickListener {
            presenter.onClickedButtonTakePhoto()
        }
    }
}
