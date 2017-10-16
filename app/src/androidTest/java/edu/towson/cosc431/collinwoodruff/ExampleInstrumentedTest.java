package edu.towson.cosc431.collinwoodruff;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.towson.cosc431.collinwoodruff.labs.MainActivity;
import edu.towson.cosc431.collinwoodruff.labs.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void AddSongButtonDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("Add Song")).check(matches(isDisplayed()));
    }

    @Test
    public void DeleteButtonDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("Delete")).check(matches(isDisplayed()));
    }

    @Test
    public void IsAwesomeCheckboxDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("Is Awesome")).check(matches(isDisplayed()));
    }
}
