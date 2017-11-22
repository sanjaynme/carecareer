package au.com.carecareers.android.profileModule.preferredLocation;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.model.CandidateModel;
import butterknife.BindView;

public class PreferredLocationActivity extends BaseActivity implements OnMapReadyCallback {
    private final String TAG = "PreferredLocation";
    private final int DEFAULT_ZOOM = 15;
    @Inject
    Gson gson;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    private SupportPlaceAutocompleteFragment autocompleteFragment;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private CandidateModel mCandidateModel = new CandidateModel();

    public static void startForResult(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, PreferredLocationActivity.class);
        activity.startActivityForResult(intent, AppContract.RequestCode.PREFERRED_LOCATION);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_preferred_location;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        loadPlaceAutocompleteFragment();
        loadMapFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferred_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_done:
                if (mCandidateModel.address == null) {
                    showAlertDialog(R.string.err_no_location_selected);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(AppContract.Extras.DATA, gson.toJson(mCandidateModel));
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppContract.Permission.LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLocationUI();
                }
            }
        }
    }

    private void loadPlaceAutocompleteFragment() {
        autocompleteFragment = (SupportPlaceAutocompleteFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        autocompleteFragment.getView().findViewById(R.id.place_autocomplete_clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCandidateModel.address = null;
                mGoogleMap.clear();
                autocompleteFragment.setText("");
            }
        });

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                addMarker(place.getLatLng(), false);
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    private void loadMapFragment() {
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker(latLng, true);
            }
        });
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.title_preferred_location));
    }


    /*
    * 1) Update the location button visibility as per the location permission status
    * 2) If location permission is not granted, request permission
    * */
    private void updateLocationUI() {
        if (mGoogleMap == null) {
            return;
        }
        try {
            if (isLocationPermissionGranted()) {
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mGoogleMap.setMyLocationEnabled(false);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                requestLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /*
    * Get the best and most recent location of the device, which may be null in rare
    * cases when a location is not available.
    *  Cases: if last known location is not available move camera to default location
    * */
    private void getDeviceLocation() {
        try {
            if (isLocationPermissionGranted()) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation != null) {
                                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastKnownLocation.getLatitude(),
                                                mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void addMarker(LatLng latLng, boolean updateAutoCompleteFragment) {
        try {
            Geocoder myLocation = new Geocoder(PreferredLocationActivity.this, Locale.getDefault());
            List<Address> myList = myLocation.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (myList != null && !myList.isEmpty()) {
                Address address = myList.get(0);
                String addressStr = address.getAddressLine(0);
                String[] placeName = address.getAddressLine(0).split(",");

                mGoogleMap.clear();
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(placeName[0]));
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
                if (updateAutoCompleteFragment) {
                    autocompleteFragment.setText(placeName[0]);
                }
                mCandidateModel.address = new CandidateModel.Address();
                mCandidateModel.address.address = addressStr;
                mCandidateModel.address.formatted = addressStr;
                mCandidateModel.address.city = address.getLocality();
                mCandidateModel.address.state = address.getAdminArea();
                mCandidateModel.address.country = address.getCountryCode();
                mCandidateModel.address.postcode = address.getPostalCode();
                mCandidateModel.address.latitude = String.valueOf(address.getLatitude());
                mCandidateModel.address.longitude = String.valueOf(address.getLongitude());
                mCandidateModel.address.mapZoom = DEFAULT_ZOOM;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                AppContract.Permission.LOCATION);
    }
}
