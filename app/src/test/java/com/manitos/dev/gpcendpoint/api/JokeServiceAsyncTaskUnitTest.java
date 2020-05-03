package com.manitos.dev.gpcendpoint.api;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by gilberto hdz on 02/05/20.
 */
@RunWith(JUnit4.class)
public class JokeServiceAsyncTaskUnitTest {

    @Mock
    JokeServiceAsyncTask mockJokeServiceAsyncTask;

    @Mock
    JokeServiceAsyncTask.JokeValueFetcherListener jokeValueFetcherListener;

    @Mock
    Context mCtx = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockJokeServiceAsyncTask = new JokeServiceAsyncTask(jokeValueFetcherListener);
    }

    @Test
    public void testAsyncTask() throws InterruptedException, ExecutionException, TimeoutException {
        mockJokeServiceAsyncTask.start(mCtx);

        JokeServiceAsyncTask.Result joke = mockJokeServiceAsyncTask.get(5, TimeUnit.SECONDS);

        Assert.assertTrue(joke == null);
    }
}