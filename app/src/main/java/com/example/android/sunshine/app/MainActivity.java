package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "ON_CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        Log.v(LOG_TAG, "ON_PAUSE");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(LOG_TAG, "ON_STOP");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG, "ON_RESUME");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.v(LOG_TAG, "ON_START");
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "ON_DESTROY");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else if ( id == R.id.action_map) {

            openPreferredLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        Intent intent = new Intent( Intent.ACTION_VIEW);
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String location = pref.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                                .appendQueryParameter("q", location)
                                .build();

        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null ) {
            startActivity(intent);
        }
        else {
            Log.d("TAGTAG", "Coouldn;t call " + location + ", no recieiving apps");
        }
    }

}
