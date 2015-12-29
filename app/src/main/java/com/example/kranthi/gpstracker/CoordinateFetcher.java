package com.example.kranthi.gpstracker;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kranthi on 11/29/2015.
 */
public class CoordinateFetcher  extends  Thread{

    private SendCoordinates sendCoordinates = null;
    private GPSTracker mGPS = null;
    private Location location = null;
    private EditText editText = null;
    private EditText editText1 = null;
    private Activity parent;
    private Double latt = null , longg = null;
    CoordinateFetcher(Context context,EditText editText,EditText editText1,Activity parent){
        this.editText = editText;
        this.parent = parent;
        this.editText1 = editText1;
        mGPS = new GPSTracker(context);
        sendCoordinates = new SendCoordinates();
    }


    @Override
    public void run() {

       TextView textView = (TextView) parent.findViewById(R.id.textView);
        while (true) {
            //System.out.println("Running thread at: "+new Date() +" Lattt: "+latt);

            try {
                if (mGPS.canGetLocation && mGPS.location != null) {

                    location = mGPS.getLocation();
                    latt =  mGPS.location.getLatitude();
                    longg =  mGPS.location.getLongitude();

                    textView.setText("Latitude:\t[ " + latt + " ] \n Longitude\t[" + longg + " ]");
                }
                setLatt(latt);
                sendCoordinates.sendCoordinates(editText.getText().toString()+"?location=" + latt.toString() + "," + longg.toString());
                Thread.sleep(1000L * Integer.parseInt(editText1.getText().toString()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized  Double getCoordinates(){
        return getLatt();
    }

    public Double getLatt() {
        return latt;
    }

    public void setLatt(Double latt) {
        this.latt = latt;
    }

    public Double getLongg() {
        return longg;
    }

    public void setLongg(Double longg) {
        this.longg = longg;
    }
}

