package com.ldc.android.travelchecker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ldc.android.travelchecker.ui.login.LoginActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TripDetailTest {


    @get:Rule
    val rule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun TestTripDetailText() {
        onView(withId(R.id.username)).perform(typeText("username"))
        onView(withId(R.id.password)).perform(typeText("password"))
        onView(withId(R.id.login)).check(matches(isEnabled()))
    }
}