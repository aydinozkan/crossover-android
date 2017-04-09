package com.aydozkan.crossover.gui.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;

import com.aydozkan.crossover.R;
import com.aydozkan.crossover.gui.fragment.PaymentFragmentDialog;
import com.aydozkan.crossover.network.models.pojo.Place;
import com.aydozkan.crossover.presenter.PlacesPresenter;
import com.aydozkan.crossover.view.PlacesView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Displays a Google map with markers.
 * <p>
 * When GoogleMap becomes ready, PlacesService gets invoked and returns Markers which are going to be added to GoogleMap
 * <p>
 * Implements OnMapReadyCallBack: Indicates that GoogleMap is ready.
 * Implements PlacesView: PlacesService CallBack Interface
 */
public class PlacesActivity extends BaseActivity implements OnMapReadyCallback, PlacesView, PaymentFragmentDialog.RentPaymentListener {

    private static final int CAMERA_BOUNDS_PADDING = 100;

    private PlacesPresenter mPlacesPresenter;
    private GoogleMap mGoogleMap;
    private SupportMapFragment mSupportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize PlacesPresenter
        mPlacesPresenter = new PlacesPresenter(this);

        setContentView(R.layout.activity_map);

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //Get Map
        mSupportMapFragment.getMapAsync(this);
    }

    /**
     * Prompts a Logout AlertDialog
     *
     * @see BaseActivity
     */
    @Override
    public void onBackPressed() {
        logoutAlertDialog();
    }

    /**
     * Calls GetPlaces when GoogleMap is available.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        //Clicking on an InfoWindow will create and show a new instance of PaymentDialogFragment
        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                DialogFragment paymentDialogFragment = PaymentFragmentDialog.newInstance(marker.getTitle());
                paymentDialogFragment.show(getSupportFragmentManager(), getString(R.string.payment_dialog));
            }
        });

        //GetPlaces Service Call
        if (mPlacesPresenter != null) {
            mPlacesPresenter.getPlaces();
        }
    }

    /**
     * Gets invoked when getPlaces Service Call is Successful and returns a list of Places.
     * Adds all places to mGoogleMap and creates a builder with all places for the Camera to be animated.
     *
     * @param placeList List of Places
     * @see com.aydozkan.crossover.network.service.PlacesService
     */
    @Override
    public void onGetPlaces(@NonNull List<Place> placeList) {
        //Custom marker icon
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.bicycle_marker));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        LatLng latLng;

        //Add all markers to GoogleMap
        for (Place place : placeList) {
            latLng = new LatLng(place.getLocation().getLatitude(), place.getLocation().getLongitude());

            mGoogleMap.addMarker(
                    new MarkerOptions().position(latLng).title(place.getName()).icon(bitmapDescriptor).snippet(getString(R.string.action_rent_bike)));

            builder.include(latLng);
        }

        LatLngBounds bounds = builder.build();

        //Zoom until all markers are in camera and give padding (CAMERA_BOUNDS_PADDING)
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, CAMERA_BOUNDS_PADDING));
    }

    /**
     * Gets invoked when rentPayment service call is Successful.
     * Displays a message that the Payment has been done.
     *
     * @param message String message response from rentPayment service
     * @see com.aydozkan.crossover.network.service.PlacesService
     */
    @Override
    public void onRentPayment(String message) {
        if (mSupportMapFragment != null && mSupportMapFragment.getView() != null) {
            Snackbar.make(mSupportMapFragment.getView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Interface method by PaymentFragmentDialog
     * Gets Invoked when mBtnRent is clicked.
     *
     * @param cardNumber     Credit Card Number
     * @param cardOwnerName  Credit Card Owner Name
     * @param expirationDate Credit Card Expiration Date (MM/YYYY)
     * @param securityCode   Credit Card Security Code
     * @see PaymentFragmentDialog
     */
    @Override
    public void onRentPaymentButtonClicked(String cardNumber, String cardOwnerName, String expirationDate, String securityCode) {
        if (mPlacesPresenter != null) {
            mPlacesPresenter.rentPayment(cardNumber, cardOwnerName, expirationDate, securityCode);
        }
    }
}
