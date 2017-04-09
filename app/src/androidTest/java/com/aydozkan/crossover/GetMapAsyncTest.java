package com.aydozkan.crossover;

import android.support.test.rule.ActivityTestRule;

import com.aydozkan.crossover.gui.activity.PlacesActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GetMapAsyncTest extends ActivityTestRule<PlacesActivity> implements OnMapReadyCallback {

    @Rule
    public final ActivityTestRule<PlacesActivity> mActivityRule = new ActivityTestRule<>(
            PlacesActivity.class);

    public GetMapAsyncTest() {
        super(PlacesActivity.class);
    }

    @Test
    public void getMapAsync() throws Exception {
        final SupportMapFragment mapFragment = (SupportMapFragment) mActivityRule.getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapFragment.getMapAsync(GetMapAsyncTest.this);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        assertTrue(googleMap != null);
    }
}