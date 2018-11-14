package com.locationbasedapp.elisa.stopandgo.location;

import android.support.annotation.NonNull;

public interface ISettingsClientResultListener {
    void onSuccess();

    void onFailure(@NonNull final String message);
}

