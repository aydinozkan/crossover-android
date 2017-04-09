package com.aydozkan.crossover;

import com.aydozkan.crossover.utility.Utility;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UtilityUnitTest {

    @Test
    public void validateEmail_Success() {
        assertTrue(Utility.isValidEmail("dummy@example.com"));
    }

    @Test
    public void validateInvalidEmail_Failure() {
        assertTrue(!Utility.isValidEmail("dummy"));
    }
}