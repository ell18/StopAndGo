package com.locationbasedapp.elisa.stopandgo.location;

import android.Manifest;
import android.app.Activity;
import android.location.Location;
import android.support.annotation.NonNull;

import java.util.concurrent.CopyOnWriteArrayList;

import com.locationbasedapp.elisa.stopandgo.Service.Settings;

public class LocationManager {

    private final GooglePlayServicesLocationSource mFusedLocationSource;
    private CopyOnWriteArrayList<ILocationUpdatesListener> mSubscribers =
            new CopyOnWriteArrayList<>();
    private PermissionManager mPermissionManager;

    LocationManager(@NonNull final Activity activity, @NonNull final Settings settings,
                    @NonNull final SettingsClientManager settingsClientManager) {
        mPermissionManager = new PermissionManager(activity, settings);
        mFusedLocationSource = new GooglePlayServicesLocationSource(activity, mPermissionManager,
                settingsClientManager, this);
    }

    //@Override
    public void onLocationChanged(@NonNull Location location) {
        if (mSubscribers.isEmpty()) {
            return;
        }
        for (ILocationUpdatesListener listener : mSubscribers) {
            listener.onLocationChanged(location);
        }
    }

    public void getLastLocation(@NonNull final ILastLocationListener listener) {
        mFusedLocationSource.getLastLocation(listener);
    }

    public void subscribeToLocationChanges(ILocationUpdatesListener listener) {
        if (mSubscribers.isEmpty()) {
            mFusedLocationSource.startReceivingLocationUpdates();
        }
        mSubscribers.add(listener);
    }

    public void unSubscribeToLocationChanges(ILocationUpdatesListener listener) {
        mSubscribers.remove(listener);
        if (mSubscribers.isEmpty()) {
            mFusedLocationSource.stopReceivingLocationUpdates();
        }
    }

    public boolean hasLocationPermission() {
        return mPermissionManager.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }
    void notifyPermissionRequestResults(String[] permissions, int[] grantResults) {
        int index = 0;
        for (String permission: permissions) {
            switch(permission) {
                case Manifest.permission_group.LOCATION:
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    int grantResult = grantResults[index];
                    if (Activity.RESULT_OK == grantResult) {
                        mFusedLocationSource.startReceivingLocationUpdates();
                    } else if (Activity.RESULT_CANCELED == grantResult) {
                        mFusedLocationSource.stopReceivingLocationUpdates();
                    }
                    break;
            }
            index++;
        }
    }
}