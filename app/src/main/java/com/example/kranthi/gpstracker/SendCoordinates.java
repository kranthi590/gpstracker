package com.example.kranthi.gpstracker;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kranthi on 11/27/2015.
 */
public class SendCoordinates {

    public final static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss a";

    public static void sendCoordinates(String server_url) {

        try {
            /*URL url = new URL(server_url);

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(10 * 1000);
            urlc.connect();*/
            //System.out.println("Sending Data at: " +getCurrentTimeStamp());
            new URL(server_url).openStream();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        } finally {

        }

    }

     public static String getCurrentTimeStamp() {

        String date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Calendar cal = Calendar.getInstance();
            date = dateFormat.format(cal.getTime());
            // logger.info("returning date: " + date);
        } catch (Exception e) {

        }

        return date;
    }
}
