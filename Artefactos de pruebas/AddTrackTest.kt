package co.edu.uniandes.app.movil202215

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.edu.uniandes.app.movil202215.view.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.time.LocalDateTime


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class AddTrackTest {


    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun addTrackHu08() {

        Thread.sleep(10000)
        onView(allOf(withResourceName("albumsFragment"),
            isDisplayed()))
            .perform(click())

        val name = "Buscando América"

        onView(allOf(withId(R.id.card_view_layout_text_title), withText(name))).perform(click())

        Thread.sleep(2000)

        onView(allOf(withResourceName("add_track_floating"), isDisplayed())).perform(click())
        Thread.sleep(2000)

        val trackName = System.currentTimeMillis().toString()
        onView(allOf(withResourceName("add_song_name")))
            .perform(scrollTo(),typeText(trackName))

        onView(allOf(withResourceName("add_song_duration")))
            .perform(scrollTo(),typeText("3:00"))

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(2000)

        onView(allOf(withResourceName("add_song_button"))).perform(scrollTo(), click())

        Thread.sleep(2000)

    }
}