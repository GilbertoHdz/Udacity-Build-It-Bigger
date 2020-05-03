package com.manitos.dev.gpcendpoint.api;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import androidx.annotation.VisibleForTesting;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.manitos.dev.backend.myApi.MyApi;

import java.io.IOException;

/**
 * States:
 *  ""   - indicates that the JokeValue has not yet been fetched
 *  null - indicates that the JokeValue retrieval failed
 *  otherwise mJokeValue will contain the JokeValue
 *
 *  Created by gilbertohdz on 02/05/20.
 */
@VisibleForTesting
public class JokeServiceAsyncTask extends AsyncTask<Context, Void, JokeServiceAsyncTask.Result> {

    private static final int JOKE_VALUE_FETCH_TIMEOUT = 5000;

    private MyApi myApiService = null;

    private boolean mStartCalled = false;

    private JokeValueFetcherListener mListener;

    public interface JokeValueFetcherListener {
        void onJokeValue(Result jokeResult);
    }

    public JokeServiceAsyncTask(JokeValueFetcherListener listener) {
        this.mListener = listener;
    }

    public void start(final Context context) {
        mStartCalled = true;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getStatus() != Status.FINISHED) {
                    JokeServiceAsyncTask.this.cancel(true);
                    JokeServiceAsyncTask.this.onPostExecute(new Result(context, null));
                }
            }
        }, JOKE_VALUE_FETCH_TIMEOUT);

        execute(context);
    }

    protected Result doInBackground(Context... params) {
        if (params.length < 1) {
            throw new IllegalArgumentException("You must include a Context object with your execute() request");
        }

        if (!mStartCalled) {
            throw new IllegalStateException("You must start the fetcher by calling start() instead of execute()");
        }

        Context context = params[0];


        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return new Result(context, myApiService.getRandomJoke().execute().getData());
        } catch (IOException e) {
            return new Result(context, null);
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        mListener.onJokeValue(result);
    }

    public class Result {
        public Context context;
        public String jokeValue;

        public Result(Context context, String jokeValue) {
            this.context = context;
            this.jokeValue = jokeValue;
        }
    }
}
