package co.edu.uniandes.app.movil202215

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.edu.uniandes.app.movil202215.view.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ArtistDetailTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun getArtistHu04() {

        Thread.sleep(10000)
        onView(allOf(withResourceName("artistFragment"),isDisplayed())).perform(click())
        Thread.sleep(3000)
        onView(allOf(withId(R.id.card_view_layout_text_title_artist), withText("Rubén Blades Bellido de Luna"),isDisplayed())).perform(click())
        Thread.sleep(3000)
        val artist1 = onView(allOf(withId(R.id.detail_layout_title_artist), withText("Rubén Blades Bellido de Luna"),isDisplayed()))
        artist1.check(ViewAssertions.matches(isDisplayed()))

        Thread.sleep(3000)

    }
}