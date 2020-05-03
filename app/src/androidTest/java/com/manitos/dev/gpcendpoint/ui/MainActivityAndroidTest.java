package com.manitos.dev.gpcendpoint.ui;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.manitos.dev.gpcendpoint.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by gilbertohdz on 02/05/20.
 */
public class MainActivityAndroidTest {

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @Before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.manitos.dev.gpcendpoint",
                appContext.getPackageName());
    }

    @Test
    public void internetConnectionSuccess() {
        onView(withId(R.id.tv_error_msg))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void isNotInternetConnection() {
        onView(withId(R.id.tv_error_msg))
                .check(matches(isDisplayed()));
    }
}