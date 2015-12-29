package com.example.kranthi.gpstracker;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    Double latt = null, longg = null;
    TextView text = null;
    private final static int INTERVAL_TIME_SECONDS = 5 * 1000; // 5 seconds
    private final static float INTERVAL_DISTANCE_METERS = 5f; // 5 meters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main);
        TextView latt = (TextView) findViewById(R.id.latt1);
        TextView longg = (TextView) findViewById(R.id.longg1);

        text = (TextView) findViewById(R.id.textView);
      /*  CoordinateFetcher coordinateFetcher = new CoordinateFetcher(mGPS.getmContext(),editText , editText1,this);
        coordinateFetcher.start();*/
      /* Thread thread = new Thread(new Runnable() {
            public void run() {

                SendCoordinates sendCoordinates = new SendCoordinates();

                Location location = null;

                while (true) {
                    //System.out.println("Running thread at: "+new Date() +" Lattt: "+latt);
                    if (mGPS.canGetLocation && mGPS.location != null) {
                        System.out.println("Getting coordinates....");
                        location = mGPS.getLocation();
                        latt =  mGPS.location.getLatitude();
                        longg =  mGPS.location.getLongitude();
                        setCoordnates(latt,longg);
                      //  setTextView("Latitude:\t[ " + latt + " ] \n Longitude\t[" + longg + " ]");
                     // MainActivity.this.text.setText("Latitude:\t[ " + latt + " ] \n Longitude\t[" + longg + " ]");
                    } else {
                        //text.setText("Unabletofind");
                        System.out.println("Unable");
                    }
                    try {
                      //  System.out.println(Integer.parseInt(editText1.getText().toString()));
                       sendCoordinates.sendCoordinates(editText.getText().toString()+"?location=" + latt.toString() + "," + longg.toString());
                        Thread.sleep(1000L * Integer.parseInt(editText1.getText().toString()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();*/

        SendCoordinates sendCoordinates = new SendCoordinates();
        LocationListener locationListener = new MyLocationListener(latt,longg, sendCoordinates);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,INTERVAL_TIME_SECONDS
                , INTERVAL_DISTANCE_METERS, locationListener);

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

    public synchronized void setCoordnates(Double latt, Double longg) {
        this.latt = latt;
        this.longg = longg;
    }

    public synchronized String getCoordinates() {

        return this.latt.toString() + "," + this.longg.toString();
    }

    public void setTextView(String str) {

        this.text.setText(str);
    }
}
