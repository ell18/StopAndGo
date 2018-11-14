package com.locationbasedapp.elisa.stopandgo.location;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.locationbasedapp.elisa.stopandgo.Service.AppServices;
import com.locationbasedapp.elisa.stopandgo.Service.Settings;

@SuppressLint("Registered")
public class BaseLocationAwareActivity extends FragmentActivity {

    protected LocationManager mLocationManager;
    private SettingsClientManager mSettingsClientManager;
    private Settings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = AppServices.getService(AppServices.SETTINGS);
        mSettingsClientManager = new SettingsClientManager(this);
        mLocationManager = new LocationManager(this, mSettings, mSettingsClientManager);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        for (String permission: permissions) {
            mSettings.writeToPreferences(permission, true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsClientManager.REQUEST_CODE_SETTINGS) {
            mSettingsClientManager.onActivityResult(resultCode, data);
        }
    }
}
