package com.manitos.dev.gpcendpoint.api;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by gilberto hdz on 02/05/20.
 */
@RunWith(JUnit4.class)
public class JokeServiceAsyncTaskUnitTest implements JokeServiceAsyncTask.JokeValueFetcherListener {

    private static String jokeTextResult;
    private static boolean called;
    private CountDownLatch signal;

    JokeServiceAsyncTask mockJokeServiceAsyncTask;

    @Mock
    Context mCtx = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        signal = new CountDownLatch(1);
    }

    @Test
    public void testRetrieveJokeTaskLocal() throws Exception {
        // Assign
        mockJokeServiceAsyncTask = new JokeServiceAsyncTask(this);

        // Action
        mockJokeServiceAsyncTask.start(mCtx);

        // Assert
        Assert.assertNotNull(jokeTextResult);
        Assert.assertFalse(jokeTextResult.isEmpty());
    }

    @Override
    public void onJokeValue(JokeServiceAsyncTask.Result jokeResult) {
        called = true;
        jokeTextResult = jokeResult.jokeValue;
        signal.countDown();
    }
}