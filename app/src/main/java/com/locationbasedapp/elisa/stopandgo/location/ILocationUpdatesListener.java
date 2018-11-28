package com.locationbasedapp.elisa.stopandgo.location;

import android.location.Location;
import android.support.annotation.NonNull;

public interface ILocationUpdatesListener {
    void onLocationChanged(@NonNull Location location);
}
