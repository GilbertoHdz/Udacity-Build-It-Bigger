package com.manitos.dev.gpcendpoint.ui;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.manitos.dev.gpcendpoint.api.JokeServiceAsyncTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by gilberto hdz on 02/05/20.
 */
@RunWith(AndroidJUnit4.class)
public class JokeServiceAsyncTaskAndroidUnitTest implements JokeServiceAsyncTask.JokeValueFetcherListener {

    private static String jokeTextResult;
    private static boolean called;
    private CountDownLatch signal;

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @Before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setup() {
        signal = new CountDownLatch(1);
    }

    @Test
    public void testRetrieveJokeTaskLocal() throws Exception {
        // Assign
        JokeServiceAsyncTask mockJokeServiceAsyncTask = new JokeServiceAsyncTask(this);

        // Action
        mockJokeServiceAsyncTask.start(activityTestRule.getActivity());
        JokeServiceAsyncTask.Result jokeTextResult = mockJokeServiceAsyncTask.get();

        // Assert
        Assert.assertNotNull(jokeTextResult);
        Assert.assertFalse(jokeTextResult.jokeValue.isEmpty());
    }

    @Override
    public void onJokeValue(JokeServiceAsyncTask.Result jokeResult) {
        called = true;
        jokeTextResult = jokeResult.jokeValue;
        signal.countDown();
    }
}