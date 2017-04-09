package com.aydozkan.crossover;

import com.aydozkan.crossover.network.models.pojo.Location;
import com.aydozkan.crossover.network.models.pojo.Place;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PojoUnitTest {

    @Test
    public void locationGetters_Success() {
        Location location = new Location(1, 1);

        assertTrue(location.getLatitude() > 0 && location.getLongitude() > 0);
    }

    @Test
    public void placeGetters_Success() {
        Place place = new Place(new Location(1, 1), "id", "name");

        assertTrue(place.getLocation() != null && place.getName() != null && !place.getName().isEmpty());
    }
}