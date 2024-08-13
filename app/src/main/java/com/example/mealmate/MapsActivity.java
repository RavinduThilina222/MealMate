package com.example.mealmate;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mealmate.database.Branch;
import com.example.mealmate.database.DatabaseHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LocationManager locationManager;
    private GoogleMap googleMap;
    private double userLat;
    private double userLng;
    private double branchLat;
    private double branchLng;
    private String branchName;
    private String branchAddress;
    private RecyclerView branchListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Initialize LocationManager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Initialize Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Initialize RecyclerView
        branchListRecyclerView = findViewById(R.id.branch_list);
        branchListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Request location updates
        requestLocationUpdates();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        // Check if location data is already available and display
        if (userLat != 0 && userLng != 0) {
            displayLocations();
        }
    }

    private void requestLocationUpdates() {
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLat = location.getLatitude();
                userLng = location.getLongitude();
                Toast.makeText(MapsActivity.this, "Location updated", Toast.LENGTH_SHORT).show();
                findNearestBranch(userLat, userLng);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void findNearestBranch(double userLatitude, double userLongitude) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Branch branchInstance = new Branch(databaseHelper);
        List<Branch> nearestBranches = branchInstance.getAllBranches();

        if (!nearestBranches.isEmpty()) {
            BranchAdapter branchAdapter = new BranchAdapter(nearestBranches);
            branchListRecyclerView.setAdapter(branchAdapter);

            Branch nearestBranch = branchInstance.getNearestBranch(userLatitude, userLongitude);
            if (nearestBranch != null) {
                branchLat = nearestBranch.getLatitude();
                branchLng = nearestBranch.getLongitude();
                branchName = nearestBranch.getName();
                branchAddress = nearestBranch.getAddress();

                // Display locations on the map
                displayLocations();
            } else {
                Log.d("NearestBranch", "No branches found.");
            }
        } else {
            Log.d("NearestBranch", "No branches found.");
        }
    }

    private void displayLocations() {
        // User location marker
        LatLng userLocation = new LatLng(userLat, userLng);
        googleMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));

        // Branch location marker
        LatLng branchLocation = new LatLng(branchLat, branchLng);
        googleMap.addMarker(new MarkerOptions().position(branchLocation).title(branchName).snippet(branchAddress));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(branchLocation, 15));
    }
}