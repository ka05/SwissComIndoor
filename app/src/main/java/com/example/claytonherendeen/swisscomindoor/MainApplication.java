package com.example.claytonherendeen.swisscomindoor;

import android.app.Application;
import android.os.Bundle;

import android.location.Location;
import com.lbs.interactor.Interactor;
import com.lbs.interactor.beacons.BackgroundHelper;
import com.lbs.interactor.events.Event;
import com.lbs.interactor.events.EventListener;
import com.lbs.interactor.exceptions.NoBluetoothException;

import org.altbeacon.beacon.Region;

public class MainApplication extends Application implements BackgroundHelper, EventListener {

    private static Interactor interactor;

    @Override
    public void onCreate() {
        super.onCreate();

        String apiKey = "9eff526c-b311-498d-b86a-67dedf6a2471";
        String serverURL = "https://interactor.swisscom.ch";

        try {
            interactor = new Interactor.Builder(this, apiKey).setServer(serverURL).build();
            interactor.registerEventListener(this);
        } catch (NoBluetoothException e) {
            e.printStackTrace();
        }
        registerActivityLifecycleCallbacks(interactor);
    }

    @Override
    public void didEnterRegion(Region region) {
        interactor.onBootStrapEnterRegion(region);
    }
    @Override
    public void didExitRegion(Region region) {
    }
    @Override
    public void didDetermineStateForRegion(int i, Region region) {
    }
    @Override
    public void onLocationChanged(Location location) {
        interactor.onLocationChanged(location);
    }

    @Override
    public void onEventTriggered(Event event) {

    }
}
