package com.manitos.dev.jokedetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class JokeDetailActivity extends AppCompatActivity {

    public static String KEY_JOKE_GCP_RESULT = "Joke.Detail.Activity.GPC.result";
    public static int KEY_ACTIVITY_RESULT = 546;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new JokeDetailFragment())
                    .commit();
        }
    }
}
