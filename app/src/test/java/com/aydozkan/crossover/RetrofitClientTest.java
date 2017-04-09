package com.aydozkan.crossover;

import com.aydozkan.crossover.network.http.RetrofitClient;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RetrofitClientTest {

    @Test
    public void getInstance_NotNull() throws Exception {
        assertTrue(RetrofitClient.getInstance() != null);
    }
}