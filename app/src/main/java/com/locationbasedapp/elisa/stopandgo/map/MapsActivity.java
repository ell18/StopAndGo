package com.locationbasedapp.elisa.stopandgo.map;

//import android.support.v4.app.FragmentActivity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.locationbasedapp.elisa.stopandgo.R;
import com.locationbasedapp.elisa.stopandgo.location.BaseLocationAwareActivity;

public class MapsActivity extends BaseLocationAwareActivity implements IMapsView,
        OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private MapsPresenter mPresenter;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        mPresenter = new MapsPresenter(this, mLocationManager);

        mRootView = findViewById(android.R.id.content);
        findViewById(R.id.floatingActionButtonLocateMe).setOnClickListener(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void moveCameraTo(Location location) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void followLocationVisibility(int visibility) {

    }
}
