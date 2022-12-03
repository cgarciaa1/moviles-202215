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
class CollectorListTest {


    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun listCollectorsHu05() {

        Thread.sleep(10000)
        onView(allOf(withResourceName("collectorsFragmentNav"),
            isDisplayed()))
            .perform(click())

        val collector1 = onView(allOf(withId(R.id.card_view_layout_text_title_collector), withText("Manolo Bellon"),isDisplayed()))
        collector1.check(ViewAssertions.matches(isDisplayed()))

        val collector2 = onView(allOf(withId(R.id.card_view_layout_text_title_collector), withText("Jaime Monsalve"),isDisplayed()))
        collector2.check(ViewAssertions.matches(isDisplayed()))

        Thread.sleep(3000)


    }
}