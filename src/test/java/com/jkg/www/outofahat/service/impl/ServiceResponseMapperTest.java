package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class ServiceResponseMapperTest {
    @Mock
    private Logger logger;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_failure() {
        Exception ex = new RuntimeException("doh");
        ServiceResponse response = ServiceResponseMapper.failure(logger, SystemEvent.PARTICIPANT_ADD_FAIL, ex);

        assertNotNull(response);
        assertFalse(response.isSuccessful());
        ErrorDetails errorDetails = response.getErrorDetails();
        assertEquals(SystemEvent.PARTICIPANT_ADD_FAIL.getId(), errorDetails.getErrorCode());
        assertTrue(errorDetails.getErrorMessage().contains(SystemEvent.PARTICIPANT_ADD_FAIL.getDescription()));
        assertTrue(errorDetails.getErrorMessage().contains(ex.getMessage()));
        verify(logger).error(anyString(), any(Exception.class));
    }
}
