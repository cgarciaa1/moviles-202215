package co.edu.uniandes.app.movil202215

import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.edu.uniandes.app.movil202215.view.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import co.edu.uniandes.app.movil202215.R


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumListTest {


    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun listAlbumsHu01() {

        Thread.sleep(10000)
        onView(allOf(ViewMatchers.withResourceName("albumsFragment"),
            isDisplayed()))
            .perform(click())

        val album1 = onView(allOf(withId(R.id.card_view_layout_text_title), withText("Buscando América"),isDisplayed()))
        album1.check(ViewAssertions.matches(isDisplayed()))

        val album2 = onView(allOf(withId(R.id.card_view_layout_text_title), withText("Poeta del pueblo"),isDisplayed()))
        album2.check(ViewAssertions.matches(isDisplayed()))

        val album3 = onView(allOf(withId(R.id.card_view_layout_text_title), withText("A Night at the Opera"),isDisplayed()))
        album3.check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(3000)


    }
}