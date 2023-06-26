package com.app.fitpeo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.AssertionFailedError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.app.fitpeo", appContext.packageName)
    }


    @Test
    fun adapterItemClick() {
        onViewEnabled(withId(R.id.progress_bar))
        Thread.sleep(2000)
    }


    private fun onViewEnabled(viewMatcher: Matcher<View>): ViewInteraction {
        val isEnabled: () -> Boolean = {
            var isDisplayed = false
            try {
                onView(viewMatcher).check(matches(not(isDisplayed)))
                isDisplayed = true

                if (isDisplayed)
                    itemClick(10)
            } catch (e: AssertionFailedError) {
                isDisplayed = false
            }
            isDisplayed
        }
        for (x in 0..9) {
            Thread.sleep(400)
            if (isEnabled()) {
                break
            }
        }
        return onView(viewMatcher)
    }

    private fun itemClick(position: Int) {

        Thread.sleep(2000)
        onView(withId(R.id.rv_images)).perform(RecyclerViewActions.scrollToPosition<ImagesAdapter.ViewHolder>(position))
        Thread.sleep(1000)
        onView(withId(R.id.rv_images)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImagesAdapter.ViewHolder>(
                position,
                click()
            )
        )
        Thread.sleep(3000)
    }

//    private fun getRandomRecyclerPosition() {
//        val ran = Random()
//
//        activityRule.scenario.onActivity {
//            val recyclerView = it.findViewById(R.id.rv_images) as RecyclerView
//
//            val n = recyclerView.adapter?.itemCount ?: 0
//
//            CoroutineScope(Dispatchers.IO).launch {
//                itemClick(ran.nextInt(n))
//            }
//        }
//    }
}