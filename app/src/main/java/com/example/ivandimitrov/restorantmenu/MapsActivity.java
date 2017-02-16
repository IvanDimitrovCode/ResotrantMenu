package com.example.ivandimitrov.restorantmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String STARTING_MARKER_LABEL = "Sofia Center";
    private GoogleMap mMap;
    private double    mLat;
    private double    mLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mLat = getIntent().getExtras().getDouble(MenuAdapter.BUNDLE_KEY_LAT);
        mLng = getIntent().getExtras().getDouble(MenuAdapter.BUNDLE_KEY_LNG);
        int stars = getIntent().getExtras().getInt(MenuAdapter.BUNDLE_KEY_STARS);
        String title = getIntent().getExtras().getString(MenuAdapter.BUNDLE_KEY_TITLE);
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(title);
        ArrayList<ImageView> imageStars = new ArrayList<>();
        imageStars.add((ImageView) findViewById(R.id.star1));
        imageStars.add((ImageView) findViewById(R.id.star2));
        imageStars.add((ImageView) findViewById(R.id.star3));
        imageStars.add((ImageView) findViewById(R.id.star4));
        imageStars.add((ImageView) findViewById(R.id.star5));
        for (int i = 0; i < imageStars.size(); i++) {
            if (stars - 1 < i) {
                imageStars.get(i).setImageResource(R.drawable.empty_star_icon);
            } else {
                imageStars.get(i).setImageResource(R.drawable.full_star_icon);
            }
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng startingCoordinates = new LatLng(mLat, mLng);
        mMap.addMarker(new MarkerOptions().position(startingCoordinates).title(STARTING_MARKER_LABEL));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startingCoordinates, 18.0f));
    }
}
