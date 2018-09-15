package com.egecius.shroomzoom

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText

import android.support.test.rule.ActivityTestRule

import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<*> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun findsHelloWorld() {
        onView(withText("Take photo")).check(matches(isDisplayed()))
    }
}
