package thanakit.ruts.ac.th.location;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

public class MyTracking  extends Service implements LocationListener {


    private final Context context;
    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    boolean canGetLocation = false;
    Location location;

    protected LocationManager locationManager;


    public MyTracking(Context context) {
        this.context = context;
    }


    public Location getLocation() {

        try {

            locationManager = (LocationManager)
                    context.getSystemService(LOCATION_SERVICE);
            isGPSEnable =
                    locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnable =
                    locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);


            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnable) {

                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
                if (location == null) {
                    if (isNetworkEnable) {

                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                1000, 10, this);
                        if (locationManager != null) {
                            location =
                                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
        return location;
    }

    //@androidx.annotation.Nullable

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}