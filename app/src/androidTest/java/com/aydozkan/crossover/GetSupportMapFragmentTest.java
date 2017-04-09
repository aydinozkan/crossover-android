package com.aydozkan.crossover;

import android.support.test.rule.ActivityTestRule;

import com.aydozkan.crossover.gui.activity.PlacesActivity;
import com.google.android.gms.maps.SupportMapFragment;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GetSupportMapFragmentTest extends ActivityTestRule<PlacesActivity> {

    @Rule
    public final ActivityTestRule<PlacesActivity> mActivityRule = new ActivityTestRule<>(
            PlacesActivity.class);

    public GetSupportMapFragmentTest() {
        super(PlacesActivity.class);
    }

    @Test
    public void getSupportMapFragment() throws Exception {

        String foundFragmentName = mActivityRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.map).getClass().getName();

        assertTrue(foundFragmentName.equals(SupportMapFragment.class.getName()));
    }
}