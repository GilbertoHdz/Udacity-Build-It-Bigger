package com.manitos.dev.gpcendpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.manitos.dev.gpcendpoint.api.network.InternetCheck;

public class MainActivity extends AppCompatActivity {

    private ProgressBar _loader;
    private TextView _error_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _loader = (ProgressBar) findViewById(R.id.pg_loader);
        _error_message = (TextView) findViewById(R.id.tv_error_msg);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        checkNetworkConnectionAndGetJokeService();
    }

    private void checkNetworkConnectionAndGetJokeService() {
        _loader.setVisibility(View.VISIBLE);
        _error_message.setVisibility(View.GONE);

        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if (internet) {
                    // Calling joke api services
                } else {
                    showErrorMessage(R.string.error_network_message);
                }
            }
        });
    }




    private void openJokeDetailScreen() {
        _loader.setVisibility(View.GONE);
        _error_message.setVisibility(View.GONE);
        // TODO IMPLEMENT ACTIVITY JOKE DETAIL
    }

    private void showErrorMessage(int resMsgId) {
        String message = getApplicationContext().getString(resMsgId);
        _error_message.setText(message);
        _error_message.setVisibility(View.VISIBLE);
        _loader.setVisibility(View.GONE);
    }
}
