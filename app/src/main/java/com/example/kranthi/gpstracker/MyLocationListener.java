package com.example.kranthi.gpstracker;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by kranthi on 11/29/2015.
 */
/*----------Listener class to get coordinates ------------- */
public class MyLocationListener implements LocationListener {
    private final static String SERVER_URL="http://183.82.95.247:8888/save/data";
    private SendCoordinates sendCoordinates  = null;
    private TextView latt = null;
    private TextView longg = null;
    MyLocationListener(TextView latt,TextView longg ,SendCoordinates sendCoordinates){
        this.sendCoordinates = sendCoordinates;
        this.latt = latt;
        this.longg = longg;
    }
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
           // System.out.println(location.getLatitude() + " latitude, " + location.getLongitude() + " longitude");
            latt.setText(location.getLatitude() + "");
            longg.setText(location.getLongitude()+"");
            sendCoordinates.sendCoordinates(SERVER_URL + "?location=" + location.getLatitude()
                    + "," + location.getLongitude()+","+SendCoordinates.getCurrentTimeStamp());

        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}


}
