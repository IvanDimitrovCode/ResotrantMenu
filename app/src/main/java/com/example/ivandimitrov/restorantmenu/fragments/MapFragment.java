package com.example.ivandimitrov.restorantmenu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivandimitrov.restorantmenu.R;
import com.example.ivandimitrov.restorantmenu.RestaurantNode;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Ivan Dimitrov on 2/14/2017.
 */

public class MapFragment extends Fragment {
    private MapView   mMapView;
    private GoogleMap googleMap;

    public static final Fragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment
                , container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        final RestaurantNode restaurant1 = new RestaurantNode("Supa Star", 42.692513, 23.330136, 5);
        final RestaurantNode restaurant2 = new RestaurantNode("Niagara", 42.678944, 23.291101, 4);
        final RestaurantNode restaurant3 = new RestaurantNode("Bordo", 42.679807, 23.239824, 3);

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                LatLng sofiaCenter = new LatLng(42.696845, 23.320943);
                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    // Defines the contents of the InfoWindow
                    @Override
                    public View getInfoContents(Marker marker) {
                        View v = getActivity().getLayoutInflater().inflate(R.layout.windowlayout, null);
                        String stars = marker.getSnippet();
                        TextView restaurantView = (TextView) v.findViewById(R.id.restaurant_name);
                        restaurantView.setText(marker.getTitle());
                        ArrayList<ImageView> imageStars = new ArrayList<>();
                        imageStars.add((ImageView) v.findViewById(R.id.star1));
                        imageStars.add((ImageView) v.findViewById(R.id.star2));
                        imageStars.add((ImageView) v.findViewById(R.id.star3));
                        imageStars.add((ImageView) v.findViewById(R.id.star4));
                        imageStars.add((ImageView) v.findViewById(R.id.star5));

                        int starsNumber = Integer.parseInt(stars);
                        for (int i = 0; i < imageStars.size(); i++) {
                            if (starsNumber - 1 < i) {
                                imageStars.get(i).setImageResource(R.drawable.empty_star_icon);
                            } else {
                                imageStars.get(i).setImageResource(R.drawable.full_star_icon);
                            }
                        }
                        return v;
                    }
                });
                googleMap.addMarker(new MarkerOptions().snippet("" + restaurant1.getStars()).position(new LatLng(restaurant1.getLat(), restaurant1.getLng())).title(restaurant1.getName()));
                googleMap.addMarker(new MarkerOptions().snippet("" + restaurant2.getStars()).position(new LatLng(restaurant2.getLat(), restaurant2.getLng())).title(restaurant2.getName()));
                googleMap.addMarker(new MarkerOptions().snippet("" + restaurant3.getStars()).position(new LatLng(restaurant3.getLat(), restaurant3.getLng())).title(restaurant3.getName()));

                CameraPosition cameraPosition = new CameraPosition.Builder().target(sofiaCenter).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
