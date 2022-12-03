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
class CreateAlbumTest {


    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun createAlbumHu07() {

        Thread.sleep(10000)
        onView(allOf(withResourceName("albumsFragment"),
            isDisplayed()))
            .perform(click())

        onView(allOf(withResourceName("add_album"),
            isDisplayed()))
            .perform(click())

        val name = "Prueba nombre " + System.currentTimeMillis()

        onView(allOf(withResourceName("input_album_name")))
            .perform(scrollTo(),typeText(name))

        onView(allOf(withResourceName("input_album_description")))
            .perform(scrollTo(),typeText("Prueba descripcion"))

        onView(allOf(withResourceName("input_album_date"))).perform(scrollTo(),
            typeText("2022-11-28"))

        onView(allOf(withResourceName("input_album_cover"))).perform(scrollTo(), typeText
            ("https://www.vibe.com/wp-content/uploads/2022/08/PSDMIJA_EC001-e1661786277689.jpg"))

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(3000)

        onView(allOf(withResourceName("button_create_album"))).perform(scrollTo(), click())

        onView(allOf(withResourceName("albumsFragment"))).perform(click())
        Thread.sleep(3000)

    }
}